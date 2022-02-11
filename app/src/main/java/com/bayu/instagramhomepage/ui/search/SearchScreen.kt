package com.bayu.instagramhomepage.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Post
import com.bayu.instagramhomepage.ui.utils.Posts
import com.bayu.instagramhomepage.ui.utils.TypePost

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar()
        SearchContent()
    }
}

@Composable
fun ColumnScope.SearchContent() {
    ExplorePosts()
}

@Composable
fun ColumnScope.ExplorePosts(
    listPosts: List<Posts> = Data.dummyDataPosts
) {
    LazyColumn {
        items(listPosts) { posts ->
            ExplorePostRow(posts = posts.post)
        }
    }
}

@Composable
fun ColumnScope.ExplorePostRow(posts: List<Post>) {
    Row(
        modifier = Modifier.weight(3F)
    ) {
        ExplorePostRowItem(post = posts[0])
        Spacer(modifier = Modifier.width(2.dp))
        ExplorePostRowItem(post = posts[1])
        Spacer(modifier = Modifier.width(2.dp))
        ExplorePostRowItem(post = posts[2])
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
fun RowScope.ExplorePostRowItem(post: Post) {
    Surface(
        contentColor = Color.White,
    ) {
        Box(
            modifier = Modifier
                .weight(1F),
            contentAlignment = Alignment.TopEnd,
        ) {
            Image(
                painter = rememberImagePainter(post.image) {
                    crossfade(true)
                },
                contentDescription = null,
                modifier = Modifier
                    .widthIn(min = 120.dp, max = 120.dp)
                    .heightIn(min = 120.dp, max = 120.dp),
                contentScale = ContentScale.Crop
            )
            when (post.typePost) {
                TypePost.Collections, TypePost.Video, TypePost.Shopping -> {
                    Icon(
                        imageVector = post.typePost.imageVector!!,
                        contentDescription = post.typePost.description,
                        modifier = Modifier
                            .padding(top = 4.dp, end = 4.dp)
                    )
                }
                TypePost.Photo -> {}
            }
        }
    }
}

@Composable
fun SearchBar() {
    Surface(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp, bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFe2e8f0),
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Search",
                color = Color(0xFF94a3b8)
            )
        }
    }
}
