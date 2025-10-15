import com.android.build.api.dsl.SettingsExtension

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

plugins {
    id("com.android.settings") version("8.9.1")
}

rootProject.name = "AndroidUtil"
include(":playground", ":android-util")

configure<SettingsExtension> {
    buildToolsVersion = "36.0.0"
    compileSdk = 36
    targetSdk = 36
    minSdk = 28
}