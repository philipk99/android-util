package de.klostermeier.androidutil.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import de.klostermeier.androidutil.compose.ButtonToggleGroup.NO_ITEM_SELECTED
import de.klostermeier.androidutil.compose.util.UiText

object ButtonToggleGroup {
    const val NO_ITEM_SELECTED = -1
}

@Composable
fun ButtonToggleGroup(
    modifier: Modifier = Modifier,
    items: List<UiText>,
    onIndexChange: (Int) -> Unit,
    defaultItem: Int = NO_ITEM_SELECTED,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    iconTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    cornerRadius: Dp = 8.dp
) = ButtonToggleGroup(
    modifier = modifier,
    items = items,
    onIndexChange = onIndexChange,
    defaultItem = defaultItem,
    itemToString = { asString() },
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    iconTint = iconTint,
    cornerRadius = cornerRadius
)

@Composable
fun <E> ButtonToggleGroup(
    modifier: Modifier = Modifier,
    items: List<E>,
    onIndexChange: (Int) -> Unit,
    defaultItem: Int = NO_ITEM_SELECTED,
    itemToString: @Composable E.() -> String = { toString() },
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    iconTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    cornerRadius: Dp = 8.dp
) {
    var selectedIndex by remember { mutableStateOf(defaultItem) }

    Row(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(weight = 1f))

        items.forEachIndexed { index, item ->
            OutlinedButton(
                onClick = {
                    selectedIndex = if (selectedIndex == index) NO_ITEM_SELECTED else index
                    onIndexChange(selectedIndex)
                },
                modifier = when (index) {
                    0 ->
                        Modifier
                            .offset(0.dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                    else ->
                        Modifier
                            .offset((-1 * index).dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStart = cornerRadius,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = 0.dp
                    )
                    items.size - 1 -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = cornerRadius,
                        bottomStart = 0.dp,
                        bottomEnd = cornerRadius
                    )
                    else -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                },
                border = BorderStroke(
                    width = 1.dp,
                    color = if (selectedIndex == index) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                    }
                ),
                colors = if (selectedIndex == index) {
                    ButtonDefaults.outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colors.primary
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors()
                }
            ) {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "ButtonToggleGroup_leadingIcon",
                        tint = iconTint
                    )
                }

                Text(
                    text = item.itemToString(),
                    color = if (selectedIndex == index) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                    },
                    modifier = Modifier.padding(horizontal = 4.dp),
                    maxLines = 1
                )

                trailingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "ButtonToggleGroup_trailingIcon",
                        tint = iconTint
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(weight = 1f))
    }
}