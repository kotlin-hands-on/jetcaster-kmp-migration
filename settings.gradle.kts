pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "Jetcaster"
include(
    ":androidApp",
    ":desktopApp",
    ":wasmApp",
    ":sharedUi",
    ":sharedLogic:data",
    ":sharedLogic:data-testing",
    ":sharedLogic:domain",
    ":sharedLogic:domain-testing",
    ":sharedLogic:designsystem"
)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
