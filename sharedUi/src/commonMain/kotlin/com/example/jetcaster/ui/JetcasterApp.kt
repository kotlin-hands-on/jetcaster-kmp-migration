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

@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.jetcaster.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.scaleOut
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetcaster.shared.Res
import com.example.jetcaster.shared.connection_error_message
import com.example.jetcaster.shared.connection_error_title
import com.example.jetcaster.shared.retry_label
import com.example.jetcaster.ui.home.MainScreen
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun JetcasterApp(
    adaptiveInfo: WindowAdaptiveInfo,
    appState: JetcasterAppState,
    buildNavGraph: NavGraphBuilder.() -> Unit = {}, // TODO temporary builder to ease migration to KMP
) {
    if (appState.isOnline) {
        SharedTransitionLayout {
            CompositionLocalProvider(
                LocalSharedTransitionScope provides this,
            ) {
                NavHost(
                    navController = appState.navController,
                    startDestination = Screen.Home.route,
                    popExitTransition = { scaleOut(targetScale = 0.9f) },
                    popEnterTransition = { EnterTransition.None },
                ) {
                    composable(Screen.Home.route) { backStackEntry ->
                        CompositionLocalProvider(
                            LocalAnimatedVisibilityScope provides this,
                        ) {
                            MainScreen(
                                windowSizeClass = adaptiveInfo.windowSizeClass,
                                navigateToPlayer = { episode ->
                                    appState.navigateToPlayer(episode.uri, backStackEntry)
                                },
                            )
                        }
                    }

                    buildNavGraph()
                }
            }
        }
    } else {
        OfflineDialog { appState.refreshOnline() }
    }
}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(Res.string.connection_error_title)) },
        text = { Text(text = stringResource(Res.string.connection_error_message)) },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(Res.string.retry_label))
            }
        },
    )
}