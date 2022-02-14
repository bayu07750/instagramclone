package com.bayu.instagramhomepage.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ActionIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier
            .size(28.dp),
    )
}