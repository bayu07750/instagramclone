package com.bayu.instagramhomepage.ui.profileuser

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bayu.instagramhomepage.ui.search.ExplorePosts

@Composable
fun Collections() {
    ExplorePosts()
}

@Composable
fun Reels() {
    Text(text = "Reels")
}

@Composable
fun Streaming() {
    Text(text = "Streaming")
}

@Composable
fun Tags() {
    Text(text = "Tags")
}

@Composable
fun ItemInfoUser(
    text: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.body2
        )
    }
}