package de.klostermeier.androidutil.validation

import de.klostermeier.androidutil.compose.util.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)