![Jetcaster logo](./docs/logo.png)

# Jetcaster: an Android app migrated to Kotlin Multiplatform 🎙️

Jetcaster is a sample podcast app made by the Android team to showcase building with Compose,
a full-featured architecture, and Material Expressive elements.
See the [original repository](https://github.com/android/compose-samples/tree/main/Jetcaster)
for an overview of its architecture and design decisions.

This repository showcases making Jetcaster multiplatform.
The resulting app runs on Android, iOS, desktop,
and in the browser, sharing everything from the data layer up to the UI.

## Reading the commit history

The commit history in the `step-by-step-migration` branch starts from an unmodified copy of the
Android app and gradually moves to the fully multiplatform app.
**Every commit is a working state** you can check out and run.

Broadly, the steps are:

1. While the app is still Android-only,
   replace Android- and JVM-only libraries with multiplatform ones
   and rewrite the code that has no multiplatform equivalent.
2. Convert the business logic modules to Kotlin Multiplatform, one module at a time,
   starting with models with the fewest dependencies.
3. Move the UI into a shared module with Compose Multiplatform, one screen at a time.
4. Add entry points for the other platforms and make them launch the shared UI.

Read the detailed overview of the steps and the decisions made in the [Migrating a Jetpack Compose app to Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform/migrate-from-android.html)
guide.

## Project structure

| Module                        | What it is                                                    |
|-------------------------------|---------------------------------------------------------------|
| `:sharedLogic:data`           | Database, network, and repositories                           |
| `:sharedLogic:data-testing`   | Test doubles for the data layer                               |
| `:sharedLogic:domain`         | Use cases, models, and the episode player                     |
| `:sharedLogic:domain-testing` | Preview and test data                                         |
| `:sharedLogic:designsystem`   | Theme, typography, fonts, and shared components               |
| `:sharedUi`                   | All of the screens, navigation, and view models               |
| `:androidApp`                 | Android entry point                                           |
| `:desktopApp`                 | Desktop (JVM) entry point                                     |
| `:wasmApp`                    | Browser (Kotlin/Wasm) entry point                             |
| `iosApp/`                     | Xcode project for the iOS entry point                         |

## Building and running

For best results, install IntelliJ IDEA or Android Studio with the [Kotlin Multiplatform plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform/versions).
The IDE can help set up the build infrastructure for you and generate run configurations for each entry point.

Building and running the iOS app requires a Mac with [Xcode](https://apps.apple.com/us/app/xcode/id497799835) installed.

## The web app

You can see the Kotlin/Wasm app built from this project [published on github.io](https://kotlin-hands-on.github.io/jetcaster-kmp-migration).
