val snapshotVersion : String? = System.getenv("COMPOSE_SNAPSHOT_ID")

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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        snapshotVersion?.let {
            println("https://androidx.dev/snapshots/builds/$it/artifacts/repository/") 
            maven { url = uri("https://androidx.dev/snapshots/builds/$it/artifacts/repository/") }
        }

        google()
        mavenCentral()
    }
}

rootProject.name = "Jetcaster"

include(
    ":androidApp",
    ":desktopApp",
    ":sharedUi",
    ":sharedLogic:data",
    ":sharedLogic:data-testing",
    ":sharedLogic:domain",
    ":sharedLogic:domain-testing",
    ":sharedLogic:designsystem"
)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
