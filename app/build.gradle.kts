plugins {
    id("com.android.application")
    id("kotlin-android")
}

val composeVersion = rootProject.extra.get("compose_version") as String

android {
    namespace = "de.klostermeier.androidutil"
    compileSdk = rootProject.extra.get("compile_sdk") as Int

    defaultConfig {
        applicationId = "de.klostermeier.androidutil"
        minSdk = rootProject.extra.get("min_sdk") as Int
        targetSdk = rootProject.extra.get("target_sdk") as Int
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":androidutil")))
}