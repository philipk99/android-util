buildscript {
    extra.apply {
        set("compose_version", "1.2.0-beta03")
        set("compile_sdk", 32)
        set("min_sdk", 29)
        set("target_sdk", 32)
    }
}

plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
