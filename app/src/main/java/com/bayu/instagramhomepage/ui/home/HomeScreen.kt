package com.bayu.instagramhomepage.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.MainViewModel
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme

internal val colorsInstagram = listOf(
    Color(0xFFDD2A7B),
    Color(0xFFFEDA77),
    Color(0xFFF58529),
)

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = viewModel(),
) {
    Column {
        TopAppBar()
        HomeContent(
            onShowBottomSheet = { mainViewModel.showBottomSheet() },
            onHideBottomSheet = { /* TODO hide bottom sheet */ }
        )
    }
}

@Composable
fun TopAppBar() {
    AppBar {
        IconButton(imageVector = Icons.Outlined.AddBox)
        IconButton(imageVector = Icons.Outlined.FavoriteBorder)
        IconButton(imageVector = Icons.Outlined.ChatBubbleOutline)
    }
}

@Composable
fun HomeContent(
    onShowBottomSheet: () -> Unit,
    onHideBottomSheet: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Stories()
        }
        item {
            Divider(modifier = Modifier.padding(top = 12.dp))
        }
        items(15) {
            Post(
                onShowBottomSheet = onShowBottomSheet,
            )
        }
    }
}

@Composable
fun Post(
    onShowBottomSheet: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        PostHeader(
            onMorePostClicked = onShowBottomSheet,
            name = "stevdza_san",
            image = painterResource(id = R.drawable.image),
        )
        PostContent(
            image = painterResource(id = R.drawable.image)
        )
        PostFooter()
    }
}

@Composable
fun PostHeader(
    onMorePostClicked: () -> Unit,
    name: String,
    image: Painter,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(start = 16.dp, end = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleBackground(
                modifier = Modifier.size(35.dp),
                border = BorderStroke(
                    width = 2.dp,
                    brush = Brush.verticalGradient(colorsInstagram)
                )
            ) {
                Surface(
                    modifier = Modifier
                        .padding(3.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                    ),
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        IconButton(imageVector = Icons.Outlined.MoreVert, onClick = onMorePostClicked)
    }
}

@Composable
fun PostContent(
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
fun PostFooter() {
    // TODO not implement yet
}

@Composable
fun Stories() {
    LazyRow {
        item {
            YourStory()
        }
        items(10) {
            Story(
                name = "neojapan_",
                painter = painterResource(id = R.drawable.image)
            )
        }
    }
}

@Composable
fun Story(
    name: String,
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        CircleBackground(
            modifier = Modifier.size(75.dp),
            border = BorderStroke(
                width = 2.dp,
                brush = Brush.verticalGradient(colorsInstagram)
            )
        ) {
            Surface(
                modifier = Modifier
                    .padding(6.dp),
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.LightGray),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        LabelStory(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    InstagramHomePageTheme {
        HomeScreen()
    }
}
