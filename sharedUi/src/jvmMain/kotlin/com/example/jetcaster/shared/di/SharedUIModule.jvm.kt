package com.example.jetcaster.shared.di

import coil3.PlatformContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedPlatformUiModule: Module = module {
    single<PlatformContext> { PlatformContext.INSTANCE }
}
