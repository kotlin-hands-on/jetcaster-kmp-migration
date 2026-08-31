package com.example.jetcaster.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual val sharedPlatformUiModule: Module = module {
    // We don't need to provide the [PlatformContext], because it's automatically provided as a typealias
}