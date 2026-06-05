package com.example.jetcaster.core.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.Default