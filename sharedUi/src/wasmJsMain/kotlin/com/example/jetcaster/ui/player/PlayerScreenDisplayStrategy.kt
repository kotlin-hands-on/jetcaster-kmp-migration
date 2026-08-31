package com.example.jetcaster.ui.player

import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
actual fun getPlayerScreenDisplayStrategy(windowSizeClass: WindowSizeClass): PlayerScreenDisplayStrategy {
    return if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
        PlayerScreenDisplayStrategy.HORIZONTAL
    } else {
        PlayerScreenDisplayStrategy.REGULAR
    }
}
