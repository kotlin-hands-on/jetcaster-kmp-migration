package com.example.jetcaster.ui.player

import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import androidx.window.layout.FoldingFeature
import com.example.jetcaster.ui.LocalDisplayFeatures
import com.example.jetcaster.util.isBookPosture
import com.example.jetcaster.util.isSeparatingPosture
import com.example.jetcaster.util.isTableTopPosture

@Composable
actual fun getPlayerScreenDisplayStrategy(windowSizeClass: WindowSizeClass): PlayerScreenDisplayStrategy {
    val foldingFeature = LocalDisplayFeatures.current.filterIsInstance<FoldingFeature>().firstOrNull()

    // Use a two pane layout if there is a fold impacting layout (meaning it is separating
    // or non-flat) or if we have a large enough width to show both.
    return if (
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) ||
        isBookPosture(foldingFeature) ||
        isTableTopPosture(foldingFeature) ||
        isSeparatingPosture(foldingFeature)
    ) {
        // Lay both sides out in a column if we are in a tabletop posture or have an
        // impactful horizontal fold. Otherwise, lay them out in a row.
        val horizontalFold = isSeparatingPosture(foldingFeature) &&
            foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL
        if (isTableTopPosture(foldingFeature) || horizontalFold) {
            PlayerScreenDisplayStrategy.VERTICAL
        } else {
            PlayerScreenDisplayStrategy.HORIZONTAL
        }
    } else {
        PlayerScreenDisplayStrategy.REGULAR
    }
}
