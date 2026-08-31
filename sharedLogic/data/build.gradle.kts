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
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.ksp)
}

kotlin {
    android {
        namespace = "com.example.jetcaster.core.data"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    jvmToolchain(17)

    iosArm64()
    iosSimulatorArm64()

    // Desktop target (JVM)
    jvm()

    sourceSets {
        commonMain.dependencies {
            // We need @Immutable annotations here
            implementation(libs.androidx.compose.runtime.annotation)

            // Dependency injection
            implementation(libs.koin.core)

            // Database
            implementation(libs.androidx.room.runtime)

            // RSS Parser library
            implementation(libs.rssparser)
            implementation(libs.kotlinx.coroutines.core)

            // Dates and times
            implementation(libs.kotlinx.datetime)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.androidx.sqlite.bundled)
        }

        jvmMain.dependencies {
            implementation(libs.androidx.sqlite.bundled)
        }

        iosMain.dependencies {
            implementation(libs.konnectivity)
            implementation(libs.androidx.sqlite.bundled)
        }
    }
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}
