<h1 align="center">AndroidUtil</h1>

<p align="center">
  <a href="http://developer.android.com/index.html"><img alt="Android" src="https://img.shields.io/badge/platform-android-green.svg"/></a>
  <a href="https://jitpack.io/#philipk99/android-util"><img alt="Version" src="https://jitpack.io/v/philipk99/android-util.svg"/></a>
  <a href="https://opensource.org/licenses/MIT"><img alt="License" src="https://img.shields.io/badge/License-MIT-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=28"><img alt="API" src="https://img.shields.io/badge/API-28%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
A small personal Android/Kotlin utility library with snippets I use across most of my projects.
Many snippets were collected from various sources over time — some are slightly altered, some are copied as-is.
The library is shared here for use with JitPack.
</p>

# Setup

Add the maven library bucket to the `dependencyResolutionManagement.repositories` block in `settings.gradle.kts` file as follows:
```kotlin
dependencyResolutionManagement {
  ...
  repositories {
    ...
    maven("https://jitpack.io")
  }
}
```

Install the library to the project in the desired module's `build.gradle.kts` file. Replace `<current_version>` with the actual version.

Option A — Direct dependency (no Version Catalog):
```kotlin
implementation("com.github.philipk99:android-util:<current_version>")
```

Option B — Using Gradle Version Catalog (libs.versions.toml):
1) Add the library to `gradle/libs.versions.toml`:
```toml
[versions]
androidUtil = "<current_version>"

[libraries]
android-util = { module = "com.github.philipk99:android-util", version.ref = "androidUtil" }
```

2) Then use the alias in your module's `build.gradle.kts`:
```kotlin
implementation(libs.android.util)
```

# Compatibility
- Kotlin 2.2.20
- Jetpack Compose BOM 2025.10.00
- Android SDK: minSdk 28

# License
MIT License — see LICENSE for details.
