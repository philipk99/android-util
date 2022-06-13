package de.klostermeier.androidutil.compose.util

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.FillSpaceBetween() = Spacer(modifier = Modifier.weight(weight = 1f))

@Composable
fun RowScope.FillSpaceBetween() = Spacer(modifier = Modifier.weight(weight = 1f))