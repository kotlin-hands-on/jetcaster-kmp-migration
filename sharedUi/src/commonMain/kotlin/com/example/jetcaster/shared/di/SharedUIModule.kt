package com.example.jetcaster.shared.di

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.request.CachePolicy
import com.example.jetcaster.core.data.di.dataModule
import com.example.jetcaster.core.data.di.otherModule
import com.example.jetcaster.core.di.domainModule
import com.example.jetcaster.shared.podcast.PodcastDetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

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

val viewModelModule = module {
    viewModel { parameters ->
        PodcastDetailsViewModel(
            podcastUri = parameters.get(),
            podcastStore = get(),
            episodeStore = get(),
            episodePlayer = get(),
        )
    }
}

val jetcasterDiModule = module {
    includes(
        sharedUiModule,
        dataModule,
        domainModule,
        otherModule,
        viewModelModule,
    )
}
