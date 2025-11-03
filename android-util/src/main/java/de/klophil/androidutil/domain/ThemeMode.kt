package de.klophil.androidutil.domain

import de.klophil.androidutil.R

enum class ThemeMode(val darkMode: Boolean?, val labelRes: Int) {
    SYSTEM(darkMode = null, labelRes = R.string.theme_mode_system),
    LIGHT(darkMode = false, labelRes = R.string.theme_mode_light),
    DARK(darkMode = true, labelRes = R.string.theme_mode_dark);

    companion object {
        fun valueOf(darkMode: Boolean?): ThemeMode = when (darkMode) {
            null -> SYSTEM
            false -> LIGHT
            true -> DARK
        }
    }
}