package de.klostermeier.androidutil.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.klostermeier.androidutil.compose.util.UiText

@Composable
fun ErrorText(
    error: UiText?
) = error?.let {
    Text(
        text = it.asString(),
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
    )
}