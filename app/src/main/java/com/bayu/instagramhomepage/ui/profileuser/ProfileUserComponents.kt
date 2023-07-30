package com.bayu.instagramhomepage.ui.profileuser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bayu.instagramhomepage.ui.search.ExplorePost
import com.bayu.instagramhomepage.ui.search.ExplorePosts
import com.bayu.instagramhomepage.ui.utils.Post

context(LazyGridScope)
fun postItems(items: List<Post>) {
    items(items = items) { post ->
        ExplorePost(post = post)
    }
}

context(LazyGridScope)
fun header(key: Any? = null, content: @Composable LazyGridItemScope.() -> Unit) {
    item(
        span = { GridItemSpan(maxCurrentLineSpan) },
        key = key,
        content = content,
    )
}

@Composable
fun Collections(modifier: Modifier = Modifier) {
    ExplorePosts(modifier = modifier)
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