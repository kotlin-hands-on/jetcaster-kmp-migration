/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetcaster.core.data.network

import com.example.jetcaster.core.data.database.model.Category
import com.example.jetcaster.core.data.database.model.Episode
import com.example.jetcaster.core.data.database.model.Podcast
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.format.DateTimeComponents
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

/**
 * A class which fetches some selected podcast RSS feeds.
 *
 * @param ioDispatcher [CoroutineDispatcher] to use for running fetch requests.
 */
class PodcastsFetcher(
    private val ioDispatcher: CoroutineDispatcher,
) {

    // Create an RSS parser
    private val rssParser = RssParser()

    /**
     * Returns a [Flow] which fetches each podcast feed and emits it in turn.
     *
     * The feeds are fetched concurrently, meaning that the resulting emission order may not
     * match the order of [feedUrls].
     */
    operator fun invoke(feedUrls: List<String>): Flow<PodcastRssResponse> {
        // We use flatMapMerge here to achieve concurrent fetching/parsing of the feeds.
        return feedUrls.asFlow()
            .flatMapMerge { feedUrl ->
                flow {
                    emit(fetchPodcast(feedUrl))
                }.catch { e ->
                    // If an exception was caught while fetching the podcast, wrap it in
                    // an Error instance.
                    emit(PodcastRssResponse.Error(e))
                }
            }
    }

    private suspend fun fetchPodcast(url: String): PodcastRssResponse {
        return withContext(ioDispatcher) {
            try {
                // Use RSS-Parser to fetch and parse the feed
                val rssChannel = rssParser.getRssChannel(url)
                rssChannel.toPodcastResponse(url)
            } catch (e: Exception) {
                PodcastRssResponse.Error(e)
            }
        }
    }
}

sealed class PodcastRssResponse {
    data class Error(val throwable: Throwable?) : PodcastRssResponse()

    data class Success(val podcast: Podcast, val episodes: List<Episode>, val categories: Set<Category>) : PodcastRssResponse()
}

/**
 * Map an RSS-Parser [RssChannel] instance to our own [PodcastRssResponse] data class.
 */
private fun RssChannel.toPodcastResponse(feedUrl: String): PodcastRssResponse {
    val podcastUri = link ?: feedUrl
    val episodes = items.mapNotNull { it.toEpisode(podcastUri) }

    val podcast = Podcast(
        uri = podcastUri,
        title = title ?: "",
        description = description,
        author = itunesChannelData?.author,
        copyright = null, // RSS-Parser doesn't provide copyright information
        imageUrl = image?.url ?: itunesChannelData?.image,
    )

    // Extract categories from iTunes data if available
    val categories = itunesChannelData?.categories
        ?.map { Category(name = it) }
        ?.toSet() ?: emptySet()

    return PodcastRssResponse.Success(podcast, episodes, categories)
}

private fun parseRssDate(dateString: String): Instant? {

    val formats = listOf(
        RFC_1123_WITH_UTC,
        DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET,
    )

    formats.forEach { format ->
        val parsed = format.parseOrNull(dateString)?.toInstantUsingOffset()
        if (parsed != null) return parsed
    }

    return null
}

/**
 * Map an RSS-Parser [RssItem] instance to our own [Episode] data class.
 */
private fun RssItem.toEpisode(podcastUri: String): Episode? {
    // Parse the publication date
    val publishedDate = try {
        pubDate?.let { dateString ->
            // Try to parse the date string to an OffsetDateTime
            parseRssDate(dateString)
        }
    } catch (e: Exception) {
        null
    }

    // If we couldn't parse the date, return null as we need a valid date
    if (publishedDate == null) return null

    return Episode(
        uri = guid ?: link ?: return null, // Need at least a guid or link
        podcastUri = podcastUri,
        title = title ?: return null, // Need a title
        author = author,
        summary = description ?: content,
        subtitle = itunesItemData?.subtitle,
        published = publishedDate,
        duration = itunesItemData?.duration?.let { parseDuration(it) },
        mediaUrls = listOfNotNull(audio, rawEnclosure?.url),
    )
}

/**
 * Parse a duration string from iTunes format to a Duration object.
 */
private fun parseDuration(durationStr: String): Duration? {
    return try {
        // Handle different formats: HH:MM:SS, MM:SS, or seconds
        val parts = durationStr.split(":")
        when (parts.size) {
            3 -> {
                // HH:MM:SS format
                val hours = parts[0].toLongOrNull() ?: 0
                val minutes = parts[1].toLongOrNull() ?: 0
                val seconds = parts[2].toLongOrNull() ?: 0
                (hours * 3600 + minutes * 60 + seconds).seconds
            }

            2 -> {
                // MM:SS format
                val minutes = parts[0].toLongOrNull() ?: 0
                val seconds = parts[1].toLongOrNull() ?: 0
                (minutes * 60 + seconds).seconds
            }

            1 -> {
                // Just seconds
                val seconds = parts[0].toLongOrNull() ?: 0
                seconds.seconds
            }

            else -> null
        }
    } catch (e: Exception) {
        null
    }
}

