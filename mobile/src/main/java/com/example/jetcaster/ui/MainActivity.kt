/*
 * Copyright 2020-2025 The Android Open Source Project
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

package com.example.jetcaster.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.example.jetcaster.core.data.network.OnlineChecker
import com.example.jetcaster.ui.player.PlayerScreen
import com.example.jetcaster.ui.theme.JetcasterTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val adaptiveInfo = currentWindowAdaptiveInfo()
            val appState = rememberJetcasterAppState(onlineChecker = koinInject<OnlineChecker>())
            CompositionLocalProvider(LocalDisplayFeatures provides calculateDisplayFeatures(this)) {
                JetcasterTheme {
                    JetcasterApp(
                        adaptiveInfo = adaptiveInfo,
                        appState = appState,
                        buildNavGraph = {
                            composable(Screen.Player.route) {
                                CompositionLocalProvider(
                                    LocalAnimatedVisibilityScope provides this,
                                ) {
                                    PlayerScreen(
                                        windowSizeClass = adaptiveInfo.windowSizeClass,
                                        onBackPress = appState::navigateBack,
                                    )
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}

// TODO this needs to be abstracted for CMP
val LocalDisplayFeatures = compositionLocalOf<List<DisplayFeature>> { emptyList() }
