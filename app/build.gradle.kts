plugins {
    id("com.android.application")
    id("kotlin-android")
}

val composeVersion = rootProject.extra.get("compose_version") as String

android {
    namespace = "de.klostermeier.androidutil_sample"
    compileSdk = rootProject.extra.get("compile_sdk") as Int

    defaultConfig {
        applicationId = "de.klostermeier.android_util"
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
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":android-util")))

    implementation("com.google.android.material:material:1.6.1")

    // Core
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Lifecycle
    val lifecycleVersion = "2.5.0-rc01"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    // Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // Accompanist
    val accompanistVersion = "0.24.10-beta"
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
}