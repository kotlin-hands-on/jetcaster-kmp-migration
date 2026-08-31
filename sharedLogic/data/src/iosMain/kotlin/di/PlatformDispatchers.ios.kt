package com.example.jetcaster.core.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO