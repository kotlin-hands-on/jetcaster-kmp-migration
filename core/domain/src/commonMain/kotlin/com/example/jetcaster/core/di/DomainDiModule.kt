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

package com.example.jetcaster.core.di

import com.example.jetcaster.core.data.JetcasterDispatchers
import com.example.jetcaster.core.data.JetcasterDispatchers.mainDispatcher
import com.example.jetcaster.core.data.repository.CategoryStore
import com.example.jetcaster.core.data.repository.EpisodeStore
import com.example.jetcaster.core.data.repository.PodcastStore
import com.example.jetcaster.core.domain.FilterableCategoriesUseCase
import com.example.jetcaster.core.domain.GetLatestFollowedEpisodesUseCase
import com.example.jetcaster.core.domain.PodcastCategoryFilterUseCase
import com.example.jetcaster.core.player.EpisodePlayer
import com.example.jetcaster.core.player.MockEpisodePlayer
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    single<EpisodePlayer> {
        MockEpisodePlayer(get<CoroutineDispatcher>(mainDispatcher))
    }

    single {
        FilterableCategoriesUseCase(
            categoryStore = get<CategoryStore>(),
        )
    }

    single {
        GetLatestFollowedEpisodesUseCase(
            episodeStore = get<EpisodeStore>(),
            podcastStore = get<PodcastStore>(),
        )
    }

    single {
        PodcastCategoryFilterUseCase(
            categoryStore = get<CategoryStore>(),
        )
    }
}