package com.example.jetcaster.shared

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.window.ComposeUIViewController
import com.example.jetcaster.core.data.network.OnlineChecker
import com.example.jetcaster.shared.di.initJetcasterDi
import com.example.jetcaster.ui.JetcasterApp
import com.example.jetcaster.ui.rememberJetcasterAppState
import com.example.jetcaster.ui.theme.JetcasterTheme
import org.koin.compose.koinInject
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName") // used from iOSApp
fun MainViewController(): UIViewController {
    initJetcasterDi()

    return ComposeUIViewController {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val appState = rememberJetcasterAppState(onlineChecker = koinInject<OnlineChecker>())
        JetcasterTheme {
            JetcasterApp(
                adaptiveInfo = adaptiveInfo,
                appState = appState,
            )
        }
    }
}