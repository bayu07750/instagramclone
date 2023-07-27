package com.bayu.instagramhomepage.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.bayu.instagramhomepage.ui.components.PopupPost
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Post
import com.bayu.instagramhomepage.ui.utils.TypePost
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    var isPopupPostVisible by remember { mutableStateOf(false) }
    var imageForPopupPost by remember { mutableStateOf("") }

    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBar()
            SearchContent { visible, image ->
                isPopupPostVisible = visible
                imageForPopupPost = image
            }
        }
        PopupPost(
            isPopupPostVisible = isPopupPostVisible,
            image = imageForPopupPost
        )
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp, bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = if (MaterialTheme.colors.isLight) Color(0xFFe2e8f0) else Color(0xFF1e293b),
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
                contentDescription = "Search Icon",
                tint = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Search",
                color = Color(0xFF94a3b8)
            )
        }
    }
}

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    onLongPressPost: (Boolean, String) -> Unit,
) {
    ExplorePosts(modifier = modifier,onLongPressPost = onLongPressPost)
}

@Composable
fun ExplorePosts(
    modifier: Modifier = Modifier,
    listPosts: List<Post> = Data.dummyDataPosts,
    onLongPressPost: ((Boolean, String) -> Unit)? = null,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(100.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(listPosts) { post ->
            ExplorePost(post = post, onLongPressPost = onLongPressPost)
        }
    }
}

@Composable
fun ExplorePost(
    post: Post,
    modifier: Modifier = Modifier,
    onLongPressPost: ((Boolean, String) -> Unit)? = null,
) {
    Surface(
        contentColor = Color.White,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.TopEnd,
        ) {
            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.image)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build(),
            )

            var isLoadImageSuccessfully by remember { mutableStateOf(false) }

            LaunchedEffect(imagePainter.state) {
                when (imagePainter.state) {
                    is AsyncImagePainter.State.Loading -> isLoadImageSuccessfully = false
                    is AsyncImagePainter.State.Success -> isLoadImageSuccessfully = true
                    else -> {}
                }
            }

            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f/1f)
                    .placeholder(
                        visible = !isLoadImageSuccessfully,
                        highlight = PlaceholderHighlight.shimmer(),
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                onLongPressPost?.invoke(true, post.image)
                            },
                            onPress = {
                                awaitRelease()
                                onLongPressPost?.invoke(false, "")
                            },
                        )
                    },
                contentScale = ContentScale.Crop
            )
            if (isLoadImageSuccessfully) {
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
}
