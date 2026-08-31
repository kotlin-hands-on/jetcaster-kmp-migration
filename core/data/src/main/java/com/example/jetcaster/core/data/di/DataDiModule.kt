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

package com.example.jetcaster.core.data.di

import androidx.room.Room
import coil.ImageLoader
import coil.request.CachePolicy
import com.example.jetcaster.core.data.BuildConfig
import com.example.jetcaster.core.data.JetcasterDispatchers
import com.example.jetcaster.core.data.JetcasterDispatchers.ioDispatcher
import com.example.jetcaster.core.data.JetcasterDispatchers.mainDispatcher
import com.example.jetcaster.core.data.database.JetcasterDatabase
import com.example.jetcaster.core.data.database.dao.TransactionRunner
import com.example.jetcaster.core.data.network.PodcastsFetcher
import com.example.jetcaster.core.data.repository.CategoryStore
import com.example.jetcaster.core.data.repository.EpisodeStore
import com.example.jetcaster.core.data.repository.LocalCategoryStore
import com.example.jetcaster.core.data.repository.LocalEpisodeStore
import com.example.jetcaster.core.data.repository.LocalPodcastStore
import com.example.jetcaster.core.data.repository.PodcastStore
import com.example.jetcaster.core.data.repository.PodcastsRepository
import java.io.File
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.LoggingEventListener
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single {
        OkHttpClient.Builder()
            .cache(Cache(File(androidContext().cacheDir, "http_cache"), (20 * 1024 * 1024).toLong()))
            .apply {
                if (BuildConfig.DEBUG) eventListenerFactory(LoggingEventListener.Factory())
            }
            .build()
    }

    single {
        Room.databaseBuilder(androidContext(), JetcasterDatabase::class.java, "data.db")
            // This is not recommended for normal apps, but the goal of this sample isn't to
            // showcase all of Room.
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        ImageLoader.Builder(androidContext())
            // Disable `Cache-Control` header support as some podcast images disable disk caching.
            .networkCachePolicy(CachePolicy.DISABLED)
            .build()
    }

    single { get<JetcasterDatabase>().categoriesDao() }
    single { get<JetcasterDatabase>().podcastCategoryEntryDao() }
    single { get<JetcasterDatabase>().podcastsDao() }
    single { get<JetcasterDatabase>().episodesDao() }
    single { get<JetcasterDatabase>().podcastFollowedEntryDao() }
    single<TransactionRunner> { get<JetcasterDatabase>().transactionRunnerDao() }

    single<CoroutineDispatcher>(ioDispatcher) { Dispatchers.IO }
    single<CoroutineDispatcher>(mainDispatcher) { Dispatchers.Main }

    single<EpisodeStore> { LocalEpisodeStore(get()) }

    single<PodcastStore> {
        LocalPodcastStore(
            podcastDao = get(),
            podcastFollowedEntryDao = get(),
            transactionRunner = get(),
        )
    }

    single<CategoryStore> {
        LocalCategoryStore(
            episodesDao = get(),
            podcastsDao = get(),
            categoriesDao = get(),
            categoryEntryDao = get(),
        )
    }

    single {
        PodcastsFetcher(
            ioDispatcher = get<CoroutineDispatcher>(ioDispatcher),
            )
    }

    single {
        PodcastsRepository(
            podcastsFetcher = get<PodcastsFetcher>(),
            podcastStore = get<PodcastStore>(),
            episodeStore = get<EpisodeStore>(),
            categoryStore = get<CategoryStore>(),
            transactionRunner = get<TransactionRunner>(),
            mainDispatcher = get<CoroutineDispatcher>(mainDispatcher),
            )
    }
}