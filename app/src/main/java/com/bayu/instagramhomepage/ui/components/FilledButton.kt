package com.bayu.instagramhomepage.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FilledButton(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
        contentColor = MaterialTheme.colors.onBackground,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            textAlign = TextAlign.Center,
        )
    }
}