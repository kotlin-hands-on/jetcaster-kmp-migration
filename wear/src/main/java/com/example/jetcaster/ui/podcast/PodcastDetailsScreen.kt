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

package com.example.jetcaster.ui.podcast

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AlertDialog
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.ListHeaderDefaults
import androidx.wear.compose.material3.PlaceholderState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.material3.placeholder
import androidx.wear.compose.material3.placeholderShimmer
import androidx.wear.compose.material3.rememberPlaceholderState
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.example.jetcaster.R
import com.example.jetcaster.core.domain.testing.PreviewPodcastEpisodes
import com.example.jetcaster.core.player.model.PlayerEpisode
import com.example.jetcaster.ui.components.MediaContent
import com.example.jetcaster.ui.preview.WearPreviewEpisodes

@Composable fun PodcastDetailsScreen(
    onPlayButtonClick: () -> Unit,
    onEpisodeItemClick: (PlayerEpisode) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    podcastDetailsViewModel: PodcastDetailsViewModel = hiltViewModel(),
) {
    val uiState by podcastDetailsViewModel.uiState.collectAsStateWithLifecycle()
    val placeholderState = rememberPlaceholderState(isVisible = uiState is PodcastDetailsScreenState.Loading)

    PodcastDetailsScreen(
        uiState = uiState,
        placeholderState = placeholderState,
        onEpisodeItemClick = onEpisodeItemClick,
        onPlayEpisode = podcastDetailsViewModel::onPlayEpisodes,
        onDismiss = onDismiss,
        onPlayButtonClick = onPlayButtonClick,
        modifier = modifier,
    )
}

@Composable
fun PodcastDetailsScreen(
    uiState: PodcastDetailsScreenState,
    placeholderState: PlaceholderState,
    onPlayButtonClick: () -> Unit,
    onEpisodeItemClick: (PlayerEpisode) -> Unit,
    onPlayEpisode: (List<PlayerEpisode>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val columnState = rememberTransformingLazyColumnState()

    ScreenScaffold(
        scrollState = columnState,
        modifier = modifier.placeholderShimmer(placeholderState),
    ) { contentPadding ->
        when (uiState) {
            is PodcastDetailsScreenState.Loaded -> {
                PodcastDetailScreenLoaded(
                    uiState.episodeList,
                    uiState.podcast.title,
                    onPlayButtonClick,
                    onPlayEpisode,
                    onEpisodeItemClick,
                    columnState,
                    contentPadding,
                    placeholderState = placeholderState,
                )
            }

            PodcastDetailsScreenState.Empty -> {
                AlertDialog(
                    visible = true,
                    onDismissRequest = onDismiss,
                    title = { stringResource(R.string.podcasts_no_episode_podcasts) },
                )
            }

            PodcastDetailsScreenState.Loading -> {
                PodcastDetailScreenLoaded(
                    emptyList(),
                    "",
                    { },
                    { },
                    { },
                    columnState,
                    contentPadding,
                    placeholderState = placeholderState,
                )
            }
        }
    }
}

@Composable
fun PodcastDetailScreenLoaded(
    episodeList: List<PlayerEpisode>,
    title: String,
    onPlayButtonClick: () -> Unit,
    onPlayEpisode: (List<PlayerEpisode>) -> Unit,
    onEpisodeItemClick: (PlayerEpisode) -> Unit,
    columnState: TransformingLazyColumnState,
    contentPadding: PaddingValues,
    placeholderState: PlaceholderState,
    modifier: Modifier = Modifier,
) {
    val transformationSpec = rememberTransformationSpec()
    TransformingLazyColumn(
        modifier = modifier,
        state = columnState,
        contentPadding = contentPadding,
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
                Text(
                    text = title, maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        item {
            ButtonsContent(
                episodes = episodeList,
                onPlayButtonClick = onPlayButtonClick,
                onPlayEpisode = onPlayEpisode,
                placeholderState = placeholderState,
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                    .transformedHeight(this, transformationSpec),
                transformation = SurfaceTransformation(transformationSpec),
            )
        }
        items(episodeList) { episode ->
            MediaContent(
                episode = episode,
                episodeArtworkPlaceholder = painterResource(id = R.drawable.music),
                onItemClick = onEpisodeItemClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumVerticalContentPadding(ButtonDefaults.minimumVerticalListContentPadding)
                    .transformedHeight(this, transformationSpec),
                transformation = SurfaceTransformation(transformationSpec),
            )
        }
    }
}

@Composable
fun ButtonsContent(
    episodes: List<PlayerEpisode>,
    onPlayButtonClick: () -> Unit,
    onPlayEpisode: (List<PlayerEpisode>) -> Unit,
    placeholderState: PlaceholderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    transformation: SurfaceTransformation? = null,
) {
    Button(
        onClick = {
            onPlayButtonClick()
            onPlayEpisode(episodes)
        },
        enabled = enabled,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.play),
                contentDescription = stringResource(id = R.string.button_play_content_description),
            )
        },
        modifier = modifier.fillMaxWidth()
            .placeholder(placeholderState = placeholderState),
        transformation = transformation,
    ) {
        Text(stringResource(id = R.string.button_play_content_description))
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun PodcastDetailsScreenLoadedPreview(
    @PreviewParameter(WearPreviewEpisodes::class)
    episode: PlayerEpisode,
) {
    PodcastDetailsScreen(
        uiState = PodcastDetailsScreenState.Loaded(
            episodeList = listOf(episode),
            podcast = PreviewPodcastEpisodes.first().podcast,
        ),
        onPlayButtonClick = { },
        onEpisodeItemClick = {},
        onPlayEpisode = {},
        onDismiss = {},
        placeholderState = rememberPlaceholderState(isVisible = false),
    )
}
