package com.example.jetcaster.ui.player

import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass

enum class PlayerScreenDisplayStrategy { VERTICAL, HORIZONTAL, REGULAR }

/**
 * How the player screen should lay itself out. Only Android knows about folds and
 * hinges, so the decision is platform-specific.
 */
@Composable
expect fun getPlayerScreenDisplayStrategy(windowSizeClass: WindowSizeClass): PlayerScreenDisplayStrategy
