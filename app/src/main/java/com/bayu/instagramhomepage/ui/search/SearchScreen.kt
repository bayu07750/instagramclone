package com.bayu.instagramhomepage.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Post
import com.bayu.instagramhomepage.ui.utils.TypePost
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@SuppressLint("UnusedTransitionTargetStateParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    var isPopupPostVisible by remember { mutableStateOf(false) }
    var imageForPopupPost by remember { mutableStateOf("") }

    Box {
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
fun SearchContent(onLongPressPost: (Boolean, String) -> Unit) {
    ExplorePosts(onLongPressPost = onLongPressPost)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExplorePosts(
    listPosts: List<Post> = Data.dummyDataPosts,
    onLongPressPost: (Boolean, String) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(listPosts) { post ->
            ExplorePostRowItem(post = post, onLongPressPost = onLongPressPost)
        }
    }
}

@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun ExplorePostRowItem(
    post: Post,
    onLongPressPost: (Boolean, String) -> Unit,
) {
    Surface(
        contentColor = Color.White,
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.TopEnd,
        ) {
            val imagePainter = rememberImagePainter(post.image) {
                crossfade(true)
            }

            var isLoadImageSuccessfully by remember { mutableStateOf(false) }

            LaunchedEffect(imagePainter.state) {
                when (imagePainter.state) {
                    is ImagePainter.State.Loading -> {
                        isLoadImageSuccessfully = false
                    }
                    is ImagePainter.State.Success -> {
                        isLoadImageSuccessfully = true
                    }
                    else -> { /* TODO handle other state here */
                    }
                }
            }

            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .widthIn(min = 120.dp)
                    .heightIn(min = 120.dp)
                    .placeholder(
                        visible = !isLoadImageSuccessfully,
                        highlight = PlaceholderHighlight.shimmer(),
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                onLongPressPost.invoke(true, post.image)
                            },
                            onPress = {
                                awaitRelease()
                                onLongPressPost.invoke(false, "")
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
