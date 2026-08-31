/*
 * Copyright 2020 The Android Open Source Project
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

package com.example.jetcaster

import android.app.Application
import androidx.compose.runtime.Composer
import androidx.compose.runtime.ExperimentalComposeRuntimeApi
import com.example.jetcaster.core.data.di.dataModule
import com.example.jetcaster.core.data.di.otherModule
import com.example.jetcaster.core.di.domainModule
import com.example.jetcaster.di.viewModelModule
import com.example.jetcaster.shared.di.initJetcasterDi
import com.example.jetcaster.shared.di.jetcasterDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application which sets up our dependency injection with Koin.
 */
class JetcasterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        @OptIn(ExperimentalComposeRuntimeApi::class)
        Composer.setDiagnosticStackTraceEnabled(BuildConfig.DEBUG)


        initJetcasterDi {
            androidLogger()
            androidContext(this@JetcasterApplication)
            modules(viewModelModule)
        }
    }
}