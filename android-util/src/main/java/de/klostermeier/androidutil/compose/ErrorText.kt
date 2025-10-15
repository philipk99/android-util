package de.klostermeier.androidutil.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import de.klostermeier.androidutil.compose.util.UiText

@Composable
fun ErrorText(
    error: String?
) = error?.let {
    Text(
        text = it,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun ErrorText(
    error: AnnotatedString?
) = error?.let {
    Text(
        text = it,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun ErrorText(
    error: UiText?
) = error?.let {
    Text(
        text = it.asString(),
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 16.dp)
    )
}