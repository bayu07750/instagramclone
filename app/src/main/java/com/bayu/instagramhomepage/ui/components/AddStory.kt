package com.bayu.instagramhomepage.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.ui.home.CircleBackground
import com.bayu.instagramhomepage.ui.home.LabelStory

@Composable
fun AddStory(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 8.dp)
    ) {
        Box {
            CircleBackground(modifier = Modifier.size(75.dp))
            CircleBackground(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                color = Color(0xFF3b82f6),
                contentColor = MaterialTheme.colors.background,
                border = BorderStroke(2.dp, MaterialTheme.colors.background)
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