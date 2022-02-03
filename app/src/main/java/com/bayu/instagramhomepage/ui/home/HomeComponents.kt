package com.bayu.instagramhomepage.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    imageVector: ImageVector,
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
