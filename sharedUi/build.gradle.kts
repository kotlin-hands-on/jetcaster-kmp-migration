import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}


kotlin {
    androidLibrary {
        namespace = "com.example.jetcaster.sharedui"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }

    // iOS targets
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    // Desktop target (JVM)
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(projects.sharedLogic.data)
            api(projects.sharedLogic.domain)
            api(projects.sharedLogic.designsystem)
            implementation(projects.sharedLogic.domainTesting)

            implementation(libs.kotlin.stdlib)
            implementation(libs.kotlinx.coroutines.core)
            // Dependency injection
            api(libs.koin.core)
            api(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // Compose Multiplatform dependencies
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            api(libs.compose.material3)
            api(libs.compose.material3.adaptive)
            implementation(libs.compose.material3.adaptive.layout)
            implementation(libs.compose.material3.adaptive.navigation)
            implementation(libs.compose.ui)
            // TODO this needs to be added, otherwise BackHandler build fails unresolved
            implementation(libs.compose.ui.backhandler)
            implementation(libs.compose.components.ui.tooling.preview)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.navigation)

            //Image loading
            implementation(libs.coil.kt.compose)

            implementation(libs.uri.kmp)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.collections.immutable)

            implementation(libs.androidx.lifecycle.runtime.compose)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.androidx.activity.compose)
            implementation(libs.coil.network.ktor3)
            implementation(libs.androidx.window)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.coil.network.ktor3)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(compose.desktop.currentOs)
            implementation(libs.coil.network.okhttp)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.example.jetcaster.shared"
    generateResClass = auto
}
