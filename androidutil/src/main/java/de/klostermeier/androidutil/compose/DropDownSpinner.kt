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
import androidx.compose.ui.res.stringResource
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
    @StringRes defaultText: Int = R.string.drop_down_spinner_default
) = DropDownSpinner(
    modifier = modifier,
    items = items,
    onItemSelected = onItemSelected,
    defaultItem = defaultItem,
    defaultText = defaultText,
    itemToString = { asString() }
)

@Composable
fun <E> DropDownSpinner(
    modifier: Modifier = Modifier,
    items: List<E>,
    onItemSelected: (Int) -> Unit,
    defaultItem: Int = NO_ITEM_SELECTED,
    @StringRes defaultText: Int = R.string.drop_down_spinner_default,
    itemToString: @Composable E.() -> String = { toString() },
) {
    var selectedIndex by remember { mutableStateOf(defaultItem) }
    var isOpen by remember { mutableStateOf(false) }

    Box(
        modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (selectedIndex == NO_ITEM_SELECTED) {
            Text(
                text = stringResource(id = defaultText),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 3.dp),
                color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
            )
        } else {
            Text(
                text = items[selectedIndex].itemToString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 32.dp, bottom = 3.dp),
                color = MaterialTheme.colors.onSurface
            )
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
                    Text(text = item.itemToString())
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