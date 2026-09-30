package com.example.jetcaster.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.jetcaster.core.designsystem.theme.JetcasterShapes
import com.example.jetcaster.core.designsystem.theme.JetcasterTypography
import com.example.jetcaster.core.designsystem.theme.backgroundDark
import com.example.jetcaster.core.designsystem.theme.backgroundDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.backgroundDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.backgroundLight
import com.example.jetcaster.core.designsystem.theme.backgroundLightHighContrast
import com.example.jetcaster.core.designsystem.theme.backgroundLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.errorContainerDark
import com.example.jetcaster.core.designsystem.theme.errorContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.errorContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.errorContainerLight
import com.example.jetcaster.core.designsystem.theme.errorContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.errorContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.errorDark
import com.example.jetcaster.core.designsystem.theme.errorDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.errorDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.errorLight
import com.example.jetcaster.core.designsystem.theme.errorLightHighContrast
import com.example.jetcaster.core.designsystem.theme.errorLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.inverseOnSurfaceDark
import com.example.jetcaster.core.designsystem.theme.inverseOnSurfaceDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.inverseOnSurfaceDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.inverseOnSurfaceLight
import com.example.jetcaster.core.designsystem.theme.inverseOnSurfaceLightHighContrast
import com.example.jetcaster.core.designsystem.theme.inverseOnSurfaceLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.inversePrimaryDark
import com.example.jetcaster.core.designsystem.theme.inversePrimaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.inversePrimaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.inversePrimaryLight
import com.example.jetcaster.core.designsystem.theme.inversePrimaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.inversePrimaryLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.inverseSurfaceDark
import com.example.jetcaster.core.designsystem.theme.inverseSurfaceDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.inverseSurfaceDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.inverseSurfaceLight
import com.example.jetcaster.core.designsystem.theme.inverseSurfaceLightHighContrast
import com.example.jetcaster.core.designsystem.theme.inverseSurfaceLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onBackgroundDark
import com.example.jetcaster.core.designsystem.theme.onBackgroundDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onBackgroundDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onBackgroundLight
import com.example.jetcaster.core.designsystem.theme.onBackgroundLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onBackgroundLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onErrorContainerDark
import com.example.jetcaster.core.designsystem.theme.onErrorContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onErrorContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onErrorContainerLight
import com.example.jetcaster.core.designsystem.theme.onErrorContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onErrorContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onErrorDark
import com.example.jetcaster.core.designsystem.theme.onErrorDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onErrorDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onErrorLight
import com.example.jetcaster.core.designsystem.theme.onErrorLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onErrorLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryContainerDark
import com.example.jetcaster.core.designsystem.theme.onPrimaryContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryContainerLight
import com.example.jetcaster.core.designsystem.theme.onPrimaryContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryDark
import com.example.jetcaster.core.designsystem.theme.onPrimaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryLight
import com.example.jetcaster.core.designsystem.theme.onPrimaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onPrimaryLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryContainerDark
import com.example.jetcaster.core.designsystem.theme.onSecondaryContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryContainerLight
import com.example.jetcaster.core.designsystem.theme.onSecondaryContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryDark
import com.example.jetcaster.core.designsystem.theme.onSecondaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryLight
import com.example.jetcaster.core.designsystem.theme.onSecondaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onSecondaryLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceDark
import com.example.jetcaster.core.designsystem.theme.onSurfaceDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceLight
import com.example.jetcaster.core.designsystem.theme.onSurfaceLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceVariantDark
import com.example.jetcaster.core.designsystem.theme.onSurfaceVariantDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceVariantDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceVariantLight
import com.example.jetcaster.core.designsystem.theme.onSurfaceVariantLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onSurfaceVariantLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryContainerDark
import com.example.jetcaster.core.designsystem.theme.onTertiaryContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryContainerLight
import com.example.jetcaster.core.designsystem.theme.onTertiaryContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryDark
import com.example.jetcaster.core.designsystem.theme.onTertiaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryLight
import com.example.jetcaster.core.designsystem.theme.onTertiaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.onTertiaryLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.outlineDark
import com.example.jetcaster.core.designsystem.theme.outlineDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.outlineDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.outlineLight
import com.example.jetcaster.core.designsystem.theme.outlineLightHighContrast
import com.example.jetcaster.core.designsystem.theme.outlineLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.outlineVariantDark
import com.example.jetcaster.core.designsystem.theme.outlineVariantDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.outlineVariantDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.outlineVariantLight
import com.example.jetcaster.core.designsystem.theme.outlineVariantLightHighContrast
import com.example.jetcaster.core.designsystem.theme.outlineVariantLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.primaryContainerDark
import com.example.jetcaster.core.designsystem.theme.primaryContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.primaryContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.primaryContainerLight
import com.example.jetcaster.core.designsystem.theme.primaryContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.primaryContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.primaryDark
import com.example.jetcaster.core.designsystem.theme.primaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.primaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.primaryLight
import com.example.jetcaster.core.designsystem.theme.primaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.primaryLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.scrimDark
import com.example.jetcaster.core.designsystem.theme.scrimDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.scrimDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.scrimLight
import com.example.jetcaster.core.designsystem.theme.scrimLightHighContrast
import com.example.jetcaster.core.designsystem.theme.scrimLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.secondaryContainerDark
import com.example.jetcaster.core.designsystem.theme.secondaryContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.secondaryContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.secondaryContainerLight
import com.example.jetcaster.core.designsystem.theme.secondaryContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.secondaryContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.secondaryDark
import com.example.jetcaster.core.designsystem.theme.secondaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.secondaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.secondaryLight
import com.example.jetcaster.core.designsystem.theme.secondaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.secondaryLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceBrightDark
import com.example.jetcaster.core.designsystem.theme.surfaceBrightDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceBrightDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceBrightLight
import com.example.jetcaster.core.designsystem.theme.surfaceBrightLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceBrightLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerDark
import com.example.jetcaster.core.designsystem.theme.surfaceContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighDark
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighLight
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighestDark
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighestDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighestDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighestLight
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighestLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerHighestLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLight
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowDark
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowLight
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowestDark
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowestDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowestDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowestLight
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowestLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceContainerLowestLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceDark
import com.example.jetcaster.core.designsystem.theme.surfaceDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceDimDark
import com.example.jetcaster.core.designsystem.theme.surfaceDimDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceDimDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceDimLight
import com.example.jetcaster.core.designsystem.theme.surfaceDimLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceDimLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceLight
import com.example.jetcaster.core.designsystem.theme.surfaceLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceVariantDark
import com.example.jetcaster.core.designsystem.theme.surfaceVariantDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceVariantDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.surfaceVariantLight
import com.example.jetcaster.core.designsystem.theme.surfaceVariantLightHighContrast
import com.example.jetcaster.core.designsystem.theme.surfaceVariantLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryContainerDark
import com.example.jetcaster.core.designsystem.theme.tertiaryContainerDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryContainerDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryContainerLight
import com.example.jetcaster.core.designsystem.theme.tertiaryContainerLightHighContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryContainerLightMediumContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryDark
import com.example.jetcaster.core.designsystem.theme.tertiaryDarkHighContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryDarkMediumContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryLight
import com.example.jetcaster.core.designsystem.theme.tertiaryLightHighContrast
import com.example.jetcaster.core.designsystem.theme.tertiaryLightMediumContrast

internal val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

internal val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

internal val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

internal val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

internal val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

internal val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)


@Composable
expect fun colorScheme(isSystemInDark: Boolean, dynamicColor: Boolean): ColorScheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun JetcasterTheme(
    isSystemInDark: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {

    MaterialExpressiveTheme(
        colorScheme = colorScheme(isSystemInDark, dynamicColor),
        motionScheme = MotionScheme.expressive(),
        shapes = JetcasterShapes,
        typography = JetcasterTypography(),
        content = content,
    )
}