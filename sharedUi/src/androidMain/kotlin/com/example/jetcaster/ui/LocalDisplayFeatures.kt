package com.example.jetcaster.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.window.layout.DisplayFeature

val LocalDisplayFeatures = compositionLocalOf<List<DisplayFeature>> { emptyList() }