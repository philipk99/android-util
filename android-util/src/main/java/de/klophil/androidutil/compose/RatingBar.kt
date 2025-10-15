package de.klophil.androidutil.compose

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    onRatingChanged: ((Float) -> Unit)? = null,
    stars: Int = 5,
    useHalfStars: Boolean = true,
    starsColor: Color = MaterialTheme.colorScheme.primary,
    starSize: Dp = 36.dp
) {
    val haptics = LocalHapticFeedback.current

    Row(modifier = modifier) {
        val isHalfStar = rating % 1 != 0f

        for (i in 1..stars) {
            Icon(
                imageVector = if (i <= rating) {
                    Icons.Rounded.Star
                } else {
                    if (isHalfStar && (i - 0.5f) == rating) {
                        Icons.AutoMirrored.Rounded.StarHalf
                    } else {
                        Icons.Rounded.StarOutline
                    }
                },
                contentDescription = "Star $i of $stars",
                modifier = Modifier
                    .combinedClickable(
                        enabled = onRatingChanged != null,
                        indication = null,
                        interactionSource = null,
                        onClick = {
                            if (useHalfStars && !isHalfStar && i.toFloat() == rating) {
                                onRatingChanged!!(i - 0.5f)
                            } else {
                                onRatingChanged!!(i.toFloat())
                            }
                        },
                        onLongClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            onRatingChanged!!(0f)
                        }
                    )
                    .size(starSize),
                tint = starsColor
            )
        }
    }
}