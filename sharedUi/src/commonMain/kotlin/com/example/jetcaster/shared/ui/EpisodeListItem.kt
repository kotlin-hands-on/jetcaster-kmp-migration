/*
 * Copyright 2024 The Android Open Source Project
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

package com.example.jetcaster.shared.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetcaster.core.designsystem.component.HtmlTextContainer
import com.example.jetcaster.core.designsystem.component.PodcastImage
import com.example.jetcaster.core.model.EpisodeInfo
import com.example.jetcaster.core.model.PodcastInfo
import com.example.jetcaster.core.player.model.PlayerEpisode
import com.example.jetcaster.core.domain.testing.PreviewEpisodes
import com.example.jetcaster.core.domain.testing.PreviewPodcasts
import com.example.jetcaster.shared.Res
import com.example.jetcaster.shared.cd_add
import com.example.jetcaster.shared.cd_more
import com.example.jetcaster.shared.cd_play
import com.example.jetcaster.shared.episode_date_duration
import com.example.jetcaster.shared.ic_delete
import com.example.jetcaster.shared.ic_more_vert
import com.example.jetcaster.shared.ic_play_circle
import com.example.jetcaster.shared.ic_playlist_add
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.DurationUnit

@Composable
fun EpisodeListItem(
    episode: EpisodeInfo,
    podcast: PodcastInfo,
    onClick: (EpisodeInfo) -> Unit,
    removeFromQueue: (EpisodeInfo) -> Unit = {},
    onQueueEpisode: (PlayerEpisode) -> Unit,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    showPodcastImage: Boolean = true,
    showSummary: Boolean = false,
) {
    val dismissState = rememberSwipeToDismissBoxState()
    SwipeToDismissBox(
        modifier = modifier,
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 40.dp),
            ) {
                Icon(
                    painterResource(Res.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd),
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {
            Surface(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surfaceContainer,
                onClick = { onClick(episode) },
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    // Top Part
                    EpisodeListItemHeader(
                        episode = episode,
                        podcast = podcast,
                        showPodcastImage = showPodcastImage,
                        showSummary = showSummary,
                        modifier = Modifier.padding(bottom = 8.dp),
                        imageModifier = imageModifier,
                    )

                    // Bottom Part
                    EpisodeListItemFooter(
                        episode = episode,
                        podcast = podcast,
                        onQueueEpisode = onQueueEpisode,
                    )
                }
            }
        }
        when (dismissState.currentValue) {
            SwipeToDismissBoxValue.EndToStart -> {
                removeFromQueue(episode)
            }

            SwipeToDismissBoxValue.StartToEnd -> {
            }

            SwipeToDismissBoxValue.Settled -> {
            }
        }
    }
}

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
private fun EpisodeListItemFooter(
    episode: EpisodeInfo,
    podcast: PodcastInfo,
    onQueueEpisode: (PlayerEpisode) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painterResource(Res.drawable.ic_play_circle),
            contentDescription = stringResource(Res.string.cd_play),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false, radius = 24.dp),
                ) { /* TODO */ }
                .size(48.dp)
                .padding(6.dp)
                .semantics { role = Role.Button },
        )

        val localPublishedDateTime = episode.published.toLocalDateTime(TimeZone.currentSystemDefault()) //TODO: Check conversion
        val dateTimeFormat = LocalDateTime.Format {
            byUnicodePattern("uuuu/MM/dd' 'HH:mm")
        }
        val published = dateTimeFormat.format(localPublishedDateTime)
        val duration = episode.duration
        Text(
            text = when {
                duration != null -> {
                    // If we have the duration, we combine the date/duration via a
                    // formatted string
                    stringResource(
                        Res.string.episode_date_duration,
                        published,
                        duration.toInt(DurationUnit.MINUTES),
                    )
                }

                // Otherwise we just use the date
                else -> {
                    LocalDateTime.Format {
                        byUnicodePattern("uuuu/MM/dd' 'HH:mm")
                    }.format(localPublishedDateTime)
                }
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
        )

        IconButton(
            onClick = {
                onQueueEpisode(
                    PlayerEpisode(
                        podcastInfo = podcast,
                        episodeInfo = episode,
                    ),
                )
            },
        ) {
            Icon(
                painterResource(Res.drawable.ic_playlist_add),
                contentDescription = stringResource(Res.string.cd_add),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        IconButton(
            onClick = { /* TODO */ },
        ) {
            Icon(
                painterResource(Res.drawable.ic_more_vert),
                contentDescription = stringResource(Res.string.cd_more),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun EpisodeListItemHeader(
    episode: EpisodeInfo,
    podcast: PodcastInfo,
    showPodcastImage: Boolean,
    showSummary: Boolean,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
        ) {
            Text(
                text = episode.title,
                maxLines = 2,
                minLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 2.dp),
            )

            if (showSummary) {
                HtmlTextContainer(text = episode.summary) {
                    Text(
                        text = it,
                        maxLines = 2,
                        minLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            } else {
                Text(
                    text = podcast.title,
                    maxLines = 2,
                    minLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
        if (showPodcastImage) {
            EpisodeListItemImage(
                podcast = podcast,
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialTheme.shapes.medium),
                imageModifier = imageModifier,
            )
        }
    }
}

@Composable
private fun EpisodeListItemImage(podcast: PodcastInfo, modifier: Modifier = Modifier, imageModifier: Modifier = Modifier) {
    PodcastImage(
        podcastImageUrl = podcast.imageUrl,
        contentDescription = null,
        modifier = modifier,
        imageModifier = imageModifier,
    )
}

@Preview(
    name = "Light Mode",
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO, // TODO not possible in CMP
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,// TODO not possible in CMP
)
@Composable
private fun EpisodeListItemPreview() {
//    JetcasterTheme {      // TODO we haven't migrated the theme to CMP to try migrating just one screen without the overall plumbing
    EpisodeListItem(
        episode = PreviewEpisodes[0],
        podcast = PreviewPodcasts[0],
        onClick = {},
        onQueueEpisode = {},
        showSummary = true,
    )
}
//}
