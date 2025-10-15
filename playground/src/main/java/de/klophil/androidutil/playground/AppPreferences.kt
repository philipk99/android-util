package de.klophil.androidutil.playground

import kotlinx.serialization.Serializable

@Serializable
data class AppPreferences(
    val secret: String? = null
)
