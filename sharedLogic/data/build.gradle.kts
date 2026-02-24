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
    androidLibrary {
        namespace = "com.example.jetcaster.core.data"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Desktop target (JVM)
    jvm()

    sourceSets {
        commonMain.dependencies {
            // We need to provide have @Immutable annotations here
            implementation("androidx.compose.runtime:runtime-annotation:1.9.0")
            implementation("androidx.compose.runtime:runtime:1.9.0")

            // Dependency injection
            implementation(libs.koin.core)
            // Database
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            // RSS Parser library
            implementation(libs.rssparser)
            implementation(libs.kotlinx.coroutines.core)
            // Dates and times
            implementation(libs.kotlinx.datetime)

        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
//            coreLibraryDesugaring(libs.core.jdk.desugaring)
        }

        iosMain.dependencies {
            implementation(libs.konnectivity)
        }

        commonTest.dependencies {
            implementation(libs.kotlinx.test.core)
            implementation(libs.kotlinx.coroutines.test)
        }

        getByName("androidDeviceTest").dependencies {
            implementation(libs.kotlinx.test.junit)
            implementation(libs.kotlinx.test.annotations.common)
            implementation(libs.androidx.test.runner)
            implementation(libs.androidx.test.core)
            implementation(libs.androidx.test.ext.junit)
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}
