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

package com.example.jetcaster.core.domain.testing

import com.example.jetcaster.core.model.CategoryInfo
import com.example.jetcaster.core.model.EpisodeInfo
import com.example.jetcaster.core.model.PodcastInfo
import com.example.jetcaster.core.model.PodcastToEpisodeInfo
import com.example.jetcaster.core.player.model.PlayerEpisode
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import kotlin.time.Clock

val PreviewCategories = listOf(
    CategoryInfo(id = 1, name = "Crime"),
    CategoryInfo(id = 2, name = "News"),
    CategoryInfo(id = 3, name = "Comedy"),
)

val PreviewPodcasts = listOf(
    PodcastInfo(
        uri = "fakeUri://podcast/1",
        title = "Android Developers Backstage",
        author = "Android Developers",
        isSubscribed = true,
        lastEpisodeDate = Clock.System.now(),
    ),
    PodcastInfo(
        uri = "fakeUri://podcast/2",
        title = "Google Developers podcast",
        author = "Google Developers",
        lastEpisodeDate = Clock.System.now(),
    ),
)

val PreviewEpisodes = listOf(
    EpisodeInfo(
        uri = "fakeUri://episode/1",
        title = "Episode 140: Lorem ipsum dolor",
        summary = "In this episode, Romain, Chet and Tor talked with Mady Melor and Artur " +
            "Tsurkan from the System UI team about... Bubbles!",
        published = LocalDateTime(
            2020, 6, 2, 9,
            27, 0, 0,
        ).toInstant(
            FixedOffsetTimeZone(UtcOffset(-8)),
        ),
    ),
)

val PreviewPlayerEpisodes = listOf(
    PlayerEpisode(
        PreviewPodcasts[0],
        PreviewEpisodes[0],
    ),
)

val PreviewPodcastEpisodes = listOf(
    PodcastToEpisodeInfo(
        podcast = PreviewPodcasts[0],
        episode = PreviewEpisodes[0],
    ),
)
