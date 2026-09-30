package com.example.jetcaster.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun colorScheme(isSystemInDark: Boolean, dynamicColor: Boolean): ColorScheme {
    return darkScheme
}