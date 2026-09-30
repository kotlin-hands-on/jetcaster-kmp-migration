package com.example.jetcaster.shared.di

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.request.CachePolicy
import com.example.jetcaster.core.data.di.dataModule
import com.example.jetcaster.core.data.di.otherModule
import com.example.jetcaster.core.di.domainModule
import com.example.jetcaster.shared.podcast.PodcastDetailsViewModel
import com.example.jetcaster.ui.home.HomeViewModel
import com.example.jetcaster.ui.player.PlayerViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun initJetcasterDi(customInit: KoinApplication.() -> Unit = {}) {
    startKoin {
        customInit()

        modules(
            jetcasterDiModule,
            sharedViewModelModule,
        )
    }
}

expect val sharedPlatformUiModule: Module

val sharedUiModule = module {
    includes(sharedPlatformUiModule)

    single<ImageLoader> {
        ImageLoader.Builder(get<PlatformContext>())
            // Disable `Cache-Control` header support as some podcast images disable disk caching.
            .networkCachePolicy(CachePolicy.DISABLED)
            .build()
    }
}

val sharedViewModelModule = module {
    viewModel {
        HomeViewModel(
            podcastsRepository = get(),
            podcastStore = get(),
            episodeStore = get(),
            podcastCategoryFilterUseCase = get(),
            filterableCategoriesUseCase = get(),
            episodePlayer = get(),
        )
    }

    viewModel { parameters ->
        PodcastDetailsViewModel(
            podcastUri = parameters.get(),
            podcastStore = get(),
            episodeStore = get(),
            episodePlayer = get(),
        )
    }

    viewModel {
        PlayerViewModel(
            episodeStore = get(),
            episodePlayer = get(),
            savedStateHandle = get(),
        )
    }
}

val jetcasterDiModule = module {
    includes(
        sharedUiModule,
        dataModule,
        domainModule,
        otherModule,
        sharedViewModelModule,
    )
}