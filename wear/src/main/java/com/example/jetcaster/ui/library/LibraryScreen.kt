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

package com.example.jetcaster.ui.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.FilledTonalButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.ListHeaderDefaults
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.PlaceholderState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.TextDefaults
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.material3.placeholder
import androidx.wear.compose.material3.placeholderShimmer
import androidx.wear.compose.material3.rememberPlaceholderState
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import coil.compose.AsyncImage
import com.example.jetcaster.R
import com.example.jetcaster.core.model.PodcastInfo
import com.example.jetcaster.core.player.model.PlayerEpisode
import com.example.jetcaster.ui.preview.WearPreviewEpisodes
import com.example.jetcaster.ui.preview.WearPreviewPodcasts

@Composable
fun LibraryScreen(
    onLatestEpisodeClick: () -> Unit,
    onYourPodcastClick: () -> Unit,
    onUpNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    libraryScreenViewModel: LibraryViewModel = hiltViewModel(),
) {
    val uiState by libraryScreenViewModel.uiState.collectAsStateWithLifecycle()
    val placeholderState = rememberPlaceholderState(isVisible = uiState is LibraryScreenUiState.Loading)

    val columnState = rememberTransformingLazyColumnState()
    ScreenScaffold(
        scrollState = columnState,
        modifier = modifier.placeholderShimmer(placeholderState),
    ) { contentPadding ->
        when (val s = uiState) {
            is LibraryScreenUiState.Loading ->
                LibraryScreen(
                    columnState = columnState,
                    contentPadding = contentPadding,
                    onLatestEpisodeClick = { },
                    onYourPodcastClick = { },
                    onUpNextClick = { },
                    placeholderState = placeholderState,
                    queue = emptyList(),
                )

            is LibraryScreenUiState.NoSubscribedPodcast ->
                NoSubscribedPodcastScreen(
                    columnState = columnState,
                    contentPadding = contentPadding,
                    topPodcasts = s.topPodcasts,
                    onTogglePodcastFollowed = libraryScreenViewModel::onTogglePodcastFollowed,
                )

            is LibraryScreenUiState.Ready ->
                LibraryScreen(
                    columnState = columnState,
                    contentPadding = contentPadding,
                    onLatestEpisodeClick = onLatestEpisodeClick,
                    onYourPodcastClick = onYourPodcastClick,
                    onUpNextClick = onUpNextClick,
                    placeholderState = placeholderState,
                    queue = s.queue,
                )
        }
    }
}

@Composable
fun NoSubscribedPodcastScreen(
    columnState: TransformingLazyColumnState,
    topPodcasts: List<PodcastInfo>,
    onTogglePodcastFollowed: (uri: String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val transformationSpec = rememberTransformationSpec()
    TransformingLazyColumn(
        state = columnState,
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        item {
            ListHeader(
                contentColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(
                        ListHeaderDefaults.minimumTopListContentPadding,
                        ListHeaderDefaults.minimumBottomListContentPadding,
                    )
                    .transformedHeight(this, transformationSpec),
                transformation = SurfaceTransformation(transformationSpec),
            ) {
                Text(stringResource(R.string.entity_no_featured_podcasts))
            }
        }
        if (topPodcasts.isNotEmpty()) {
            items(topPodcasts.take(3)) { podcast ->
                PodcastContent(
                    podcast = podcast,
                    podcastArtworkPlaceholder = painterResource(id = R.drawable.music),
                    onClick = {
                        onTogglePodcastFollowed(podcast.uri)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                        .transformedHeight(this, transformationSpec),
                    transformation = SurfaceTransformation(transformationSpec),
                )
            }
        } else {
            item {
                PodcastContent(
                    podcast = PodcastInfo(),
                    podcastArtworkPlaceholder = painterResource(id = R.drawable.music),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                        .transformedHeight(this, transformationSpec),
                    transformation = SurfaceTransformation(transformationSpec),
                )
            }
        }
    }
}

@Composable
private fun PodcastContent(
    podcast: PodcastInfo,
    onClick: () -> Unit,
    podcastArtworkPlaceholder: Painter?,
    modifier: Modifier = Modifier,
    transformation: SurfaceTransformation? = null,
) {
    val mediaTitle = podcast.title

    FilledTonalButton(
        label = {
            Text(
                mediaTitle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        },
        onClick = { onClick() },
        icon = {
            AsyncImage(
                model = podcast.imageUrl,
                contentDescription = stringResource(R.string.latest_episodes),
                contentScale = ContentScale.Crop,
                error = podcastArtworkPlaceholder,
                placeholder = podcastArtworkPlaceholder,
                modifier = Modifier
                    .size(
                        ButtonDefaults.LargeIconSize,
                    )
                    .clip(CircleShape),
            )
        },
        modifier = modifier.fillMaxWidth(),
        transformation = transformation,
    )
}

@Composable
fun LibraryScreen(
    columnState: TransformingLazyColumnState,
    placeholderState: PlaceholderState,
    contentPadding: PaddingValues,
    onLatestEpisodeClick: () -> Unit,
    onYourPodcastClick: () -> Unit,
    onUpNextClick: () -> Unit,
    queue: List<PlayerEpisode>,
    modifier: Modifier = Modifier,
) {
    val transformationSpec = rememberTransformationSpec()
    TransformingLazyColumn(
        state = columnState,
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        item {
            ListHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(
                        ListHeaderDefaults.minimumTopListContentPadding,
                        ListHeaderDefaults.minimumBottomListContentPadding,
                    )
                    .transformedHeight(this, transformationSpec)
                    .placeholder(placeholderState),
                transformation = SurfaceTransformation(transformationSpec),
            ) {
                Text(stringResource(R.string.home_library))
            }
        }
        item {
            FilledTonalButton(
                label = { Text(stringResource(R.string.latest_episodes)) },
                onClick = { onLatestEpisodeClick() },
                icon = {
                    IconWithBackground(
                        R.drawable.new_releases,
                        stringResource(R.string.latest_episodes),
                    )
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                    .transformedHeight(this, transformationSpec)
                    .placeholder(placeholderState = placeholderState),
                transformation = SurfaceTransformation(transformationSpec),
            )
        }
        item {
            FilledTonalButton(
                label = { Text(stringResource(R.string.podcasts)) },
                onClick = { onYourPodcastClick() },
                icon = {
                    IconWithBackground(R.drawable.podcast, stringResource(R.string.podcasts))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                    .transformedHeight(this, transformationSpec)
                    .placeholder(placeholderState = placeholderState),
                transformation = SurfaceTransformation(transformationSpec),
            )
        }
        item {
            ListHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(
                        ListHeaderDefaults.minimumTopListContentPadding,
                        ListHeaderDefaults.minimumBottomListContentPadding,
                    )
                    .transformedHeight(this, transformationSpec)
                    .placeholder(placeholderState = placeholderState),
                transformation = SurfaceTransformation(transformationSpec),
            ) {
                Text(stringResource(R.string.queue))
            }
        }
        item {
            if (queue.isEmpty()) {
                QueueEmptyText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumVerticalContentPadding(
                            TextDefaults.minimumTopListContentPadding,
                            TextDefaults.minimumBottomListContentPadding,
                        )
                        .transformedHeight(this, transformationSpec),
                )
            } else {
                FilledTonalButton(
                    label = { Text(stringResource(R.string.up_next)) },
                    onClick = { onUpNextClick() },
                    icon = {
                        IconWithBackground(R.drawable.up_next, stringResource(R.string.up_next))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                        .transformedHeight(this, transformationSpec)
                        .placeholder(placeholderState = placeholderState),
                    transformation = SurfaceTransformation(transformationSpec),
                )
            }
        }
    }
}

@Composable
private fun IconWithBackground(resource: Int, contentDescription: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(ButtonDefaults.LargeIconSize)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = resource),
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(ButtonDefaults.SmallIconSize),
        )
    }
}

@Composable
private fun QueueEmptyText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.add_episode_to_queue),
        modifier = modifier,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
    )
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun LibraryScreenPreview(
    @PreviewParameter(WearPreviewEpisodes::class)
    episode: PlayerEpisode,
) {
    LibraryScreen(
        columnState = rememberTransformingLazyColumnState(),
        contentPadding = PaddingValues(),
        modifier = Modifier,
        onLatestEpisodeClick = {},
        onYourPodcastClick = {},
        onUpNextClick = {},
        queue = listOf(
            episode,
        ),
        placeholderState = rememberPlaceholderState(isVisible = false),
    )
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun PodcastContentPreview(@PreviewParameter(WearPreviewPodcasts::class) podcasts: PodcastInfo, modifier: Modifier = Modifier) {
    AppScaffold {
        ScreenScaffold {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
            ) {
                PodcastContent(
                    podcast = podcasts,
                    podcastArtworkPlaceholder = painterResource(id = R.drawable.music),
                    onClick = {},
                )
            }
        }
    }
}
