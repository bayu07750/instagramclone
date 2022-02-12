package com.bayu.instagramhomepage.ui.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.utils.ClearRippleTheme

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_instagram_logo),
                contentDescription = stringResource(R.string.desc_instagram_logo),
            )
            Row {
                actions()
            }
        }
    }
}

@Composable
fun IconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Composable
fun ColumnScope.LabelStory(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier
            .align(alignment = Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body2.copy(color = Color.Black, fontSize = 12.sp)
    )
}

@Composable
fun CircleBackground(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    color: Color = Color(0xFFf8fafc),
    contentColor: Color = MaterialTheme.colors.onBackground,
    border: BorderStroke = BorderStroke(2.dp, Color.LightGray),
    content: @Composable () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        border = border,
    ) {
        content()
    }
}

@Composable
fun YourStory() {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
    ) {
        Box {
            CircleBackground(modifier = Modifier.size(75.dp))
            CircleBackground(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                color = Color(0xFF3b82f6),
                contentColor = Color.White,
                border = BorderStroke(2.dp, Color.White)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        LabelStory(text = "Your Story")
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ToggleButton(
    checkedImageVector: ImageVector,
    unCheckedImageVector: ImageVector,
    isChecked: Boolean,
    onClick: () -> Unit
) {
    val transition = updateTransition(targetState = isChecked, label = "Checked Favorite Button")

    val tint by transition.animateColor(
        label = "Tint color",
        transitionSpec = {
            spring()
        }
    ) {
        if (it) Color.Red else Color.Black
    }

    val size by transition.animateDp(
        label = "Size Button",
        transitionSpec = {
            if (false isTransitioningTo true) {
                keyframes {
                    durationMillis = 250
                    30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                    35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                    40.dp at 75 // ms
                    35.dp at 150 // ms
                }
            } else {
                spring(stiffness = Spring.StiffnessVeryLow)
            }
        }
    ) {
        30.dp
    }

    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
        Icon(
            imageVector = if (isChecked) checkedImageVector else unCheckedImageVector,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onClick)
                .size(size),
            tint = tint,
        )
    }
}
