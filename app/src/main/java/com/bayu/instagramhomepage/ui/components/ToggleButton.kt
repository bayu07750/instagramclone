package com.bayu.instagramhomepage.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ToggleButton(
    checkedImageVector: ImageVector,
    unCheckedImageVector: ImageVector,
    colorIcon: Color = Color.Black,
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val transition = updateTransition(targetState = isChecked, label = "Checked Button")

    val tint by transition.animateColor(
        label = "Tint color",
        transitionSpec = {
            spring()
        }
    ) {
        if (it) Color.Red else colorIcon
    }

    val scale by transition.animateFloat(
        label = "scale",
        transitionSpec = {
            if (false isTransitioningTo true) {
                keyframes {
                    durationMillis = 300
                    1F at 0
                    1.2F at 75
                    1.5F at 150
                }
            } else {
                spring()
            }
        }
    ) {
        1F
    }

    Icon(
        imageVector = if (isChecked) checkedImageVector else unCheckedImageVector,
        contentDescription = null,
        modifier = modifier
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = onClick,
            )
            .size(28.dp)
            .scale(scale),
        tint = tint,
    )
}