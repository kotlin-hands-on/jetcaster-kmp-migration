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

package com.example.jetcaster.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import com.example.jetcaster.core.designsystem.Res
import com.example.jetcaster.core.designsystem.montserrat_light
import com.example.jetcaster.core.designsystem.montserrat_medium
import com.example.jetcaster.core.designsystem.montserrat_regular
import com.example.jetcaster.core.designsystem.montserrat_semibold
import com.example.jetcaster.core.designsystem.roboto_flex
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily


@Composable
fun JetcasterTypography(): Typography {
    val montserrat = FontFamily(
        Font(Res.font.montserrat_light, FontWeight.Light),
        Font(Res.font.montserrat_regular, FontWeight.Normal),
        Font(Res.font.montserrat_medium, FontWeight.Medium),
        Font(Res.font.montserrat_semibold, FontWeight.SemiBold),
    )
    val robotoFlex = FontFamily(
        Font(Res.font.roboto_flex),
    )

    return Typography(
        displayLarge = TextStyle(
            fontSize = 64.sp,
            lineHeight = 56.sp,
            fontFamily = robotoFlex,
            fontWeight = FontWeight(738),
            textAlign = TextAlign.Center,
        ),
        displayMedium = TextStyle(
            fontFamily = robotoFlex,
            fontSize = 45.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 52.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = montserrat,
            fontSize = 36.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 44.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = montserrat,
            fontSize = 32.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 40.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = montserrat,
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 36.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = montserrat,
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 32.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = montserrat,
            fontSize = 22.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 28.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = montserrat,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),
        labelMedium = TextStyle(
            fontFamily = montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = montserrat,
            fontSize = 11.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = montserrat,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = montserrat,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = montserrat,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
        ),
    )
}