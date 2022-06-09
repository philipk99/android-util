plugins {
    id("com.android.library")
    id("kotlin-android")
}

val composeVersion = rootProject.extra.get("compose_version") as String

android {
    namespace = "de.klostermeier.recipes"
    compileSdk = rootProject.extra.get("compile_sdk") as Int

    defaultConfig {
        minSdk = rootProject.extra.get("min_sdk") as Int
        targetSdk = rootProject.extra.get("target_sdk") as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    implementation("com.google.android.material:material:1.6.1")

    // Core
    implementation("androidx.core:core-ktx:1.8.0")

    // Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
}