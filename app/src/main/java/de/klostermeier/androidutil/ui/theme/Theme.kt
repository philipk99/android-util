package de.klostermeier.androidutil.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = GreyBlue300,
    primaryVariant = GreyBlue700,

    secondary = Orange600,
    secondaryVariant = Orange900,

    surface = Black800,
    background = Black800,
    error = Red200,

    onPrimary = Black900,
    onSecondary = Black900,
    onBackground = White50,
    onSurface = White50,
    onError = Black900
)

private val LightColorPalette = lightColors(
    primary = GreyBlue500,
    primaryVariant = GreyBlue900,

    secondary = Orange600,
    secondaryVariant = Orange900,

    surface = White50,
    background = White50,
    error = Red600,

    onPrimary = White50,
    onSecondary = Black900,
    onBackground = Black900,
    onSurface = Black900,
    onError = White50
)

@Composable
fun AndroidUtilTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    rememberSystemUiController().setStatusBarColor(colors.primaryVariant)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}