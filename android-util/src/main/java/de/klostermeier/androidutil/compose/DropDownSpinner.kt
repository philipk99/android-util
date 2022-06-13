package de.klostermeier.androidutil.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.klostermeier.androidutil.R
import de.klostermeier.androidutil.compose.DropDownSpinner.NO_ITEM_SELECTED
import de.klostermeier.androidutil.compose.util.UiText

object DropDownSpinner {
    const val NO_ITEM_SELECTED = -1
}

@Composable
fun DropDownSpinner(
    modifier: Modifier = Modifier,
    items: List<UiText>,
    onItemSelected: (Int) -> Unit,
    defaultItem: Int = NO_ITEM_SELECTED,
    @StringRes defaultText: Int = R.string.drop_down_spinner_default,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    iconTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    cornerRadius: Dp = 8.dp
) = DropDownSpinner(
    modifier = modifier,
    items = items,
    onItemSelected = onItemSelected,
    defaultItem = defaultItem,
    defaultText = defaultText,
    itemToString = { asString() },
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    iconTint = iconTint,
    cornerRadius = cornerRadius
)

@Composable
fun <E> DropDownSpinner(
    modifier: Modifier = Modifier,
    items: List<E>,
    onItemSelected: (Int) -> Unit,
    defaultItem: Int = NO_ITEM_SELECTED,
    @StringRes defaultText: Int = R.string.drop_down_spinner_default,
    itemToString: @Composable E.() -> String = { toString() },
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    iconTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    cornerRadius: Dp = 8.dp
) {
    var selectedIndex by remember { mutableStateOf(defaultItem) }
    var isOpen by remember { mutableStateOf(false) }

    Box(
        modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(MaterialTheme.colors.surface)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 3.dp)
        ) {
            if (selectedIndex == NO_ITEM_SELECTED) {
                Text(
                    text = stringResource(id = defaultText),
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            } else {
                DropDownSpinnerItem(
                    text = items[selectedIndex].itemToString(),
                    textColor = MaterialTheme.colors.onSurface,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    iconTint = iconTint
                )
            }
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(.85f),
            expanded = isOpen,
            onDismissRequest = {
                isOpen = false
            },
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        isOpen = false
                        selectedIndex = index.also(onItemSelected)
                    }
                ) {
                    DropDownSpinnerItem(
                        text = item.itemToString(),
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        iconTint = iconTint
                    )
                }
            }
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(24.dp),
            imageVector = Icons.Rounded.KeyboardArrowDown,
            contentDescription = "Dropdown"
        )

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .clickable(
                    onClick = { isOpen = true }
                )
        )
    }
}

@Composable
private fun DropDownSpinnerItem(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?,
    iconTint: Color
) {
    leadingIcon?.let {
        Icon(
            imageVector = it,
            contentDescription = "DropDownSpinner_leadingIcon",
            tint = iconTint,
            modifier = Modifier.padding(end = 4.dp)
        )
    }

    Text(
        text = text,
        modifier = modifier,
        color = textColor
    )

    trailingIcon?.let {
        Icon(
            imageVector = it,
            contentDescription = "DropDownSpinner_trailingIcon",
            tint = iconTint,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}