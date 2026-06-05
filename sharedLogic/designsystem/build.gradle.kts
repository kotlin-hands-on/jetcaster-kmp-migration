/*
 * Copyright 2025 The Android Open Source Project
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

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidLibrary {
        namespace = "com.example.jetcaster.core.designsystem"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()

        androidResources.enable = true
    }

    jvmToolchain(17)

    iosArm64()
    iosSimulatorArm64()

    // Desktop target (JVM)
    jvm()

    // Browser target (WASM)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            // Compose Multiplatform dependencies
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)

            //Image loading
            implementation(libs.coil.kt.compose)

            // Dependency injection
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            // HTML text converter
            implementation(libs.html.converter)

            // Dates and times
            implementation(libs.kotlinx.datetime)
        }

        androidMain.dependencies {
            implementation(libs.coil.network.okhttp)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.example.jetcaster.core.designsystem"
    generateResClass = auto
}
