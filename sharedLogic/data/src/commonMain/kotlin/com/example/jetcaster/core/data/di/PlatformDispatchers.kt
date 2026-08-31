package com.example.jetcaster.core.data.di

import kotlinx.coroutines.CoroutineDispatcher

expect fun provideIoDispatcher(): CoroutineDispatcher