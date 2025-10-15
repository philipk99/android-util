package de.klophil.androidutil.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.klophil.androidutil.R
import de.klophil.androidutil.compose.DropDownSpinner.NO_ITEM_SELECTED
import de.klophil.androidutil.compose.util.UiText

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
    iconTint: Color = LocalContentColor.current,
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
    iconTint: Color = LocalContentColor.current,
    cornerRadius: Dp = 8.dp
) {
    var selectedIndex by remember { mutableIntStateOf(defaultItem) }
    var isOpen by remember { mutableStateOf(false) }

    Box(
        modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(MaterialTheme.colorScheme.surface)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 48.dp, bottom = 3.dp)
        ) {
            if (selectedIndex == NO_ITEM_SELECTED) {
                Text(
                    text = stringResource(id = defaultText),
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "DropDownSpinner_leadingIcon",
                        tint = iconTint
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                }
                Text(
                    text = items[selectedIndex].itemToString(),
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
                trailingIcon?.let {
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Icon(
                        imageVector = it,
                        contentDescription = "DropDownSpinner_trailingIcon",
                        tint = iconTint
                    )
                }
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
                    text = {
                        Text(text = item.itemToString())
                    },
                    onClick = {
                        isOpen = false
                        selectedIndex = index.also(onItemSelected)
                    },
                    leadingIcon = {
                        leadingIcon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = "DropDownSpinner_leadingIcon",
                                tint = iconTint,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                        }
                    },
                    trailingIcon = {
                        trailingIcon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = "DropDownSpinner_trailingIcon",
                                tint = iconTint,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    },
                    colors = MenuDefaults.itemColors(
                        leadingIconColor = iconTint,
                        trailingIconColor = iconTint
                    )
                )
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