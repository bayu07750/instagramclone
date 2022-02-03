package com.bayu.instagramhomepage.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme
import kotlinx.coroutines.launch

internal val colorsInstagram = listOf(
    Color(0xFFDD2A7B),
    Color(0xFFFEDA77),
    Color(0xFFF58529),
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Column {
            TopAppBar()
            HomeContent(
                onShowBottomSheet = { scope.launch { bottomSheetState.show() } },
                onHideBottomSheet = { scope.launch { bottomSheetState.hide() } }
            )
        }
    }
}

@Composable
fun ColumnScope.BottomSheetContent() {
    Surface(
        modifier = Modifier
            .padding(top = 12.dp)
            .widthIn(min = 60.dp)
            .heightIn(min = 4.dp)
            .align(alignment = Alignment.CenterHorizontally),
        color = Color.Gray,
        shape = RoundedCornerShape(30.dp),
    ) { }
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        CircleButton(
            imageVector = Icons.Outlined.Link,
            label = "Link",
            contentDescription = "Link icon"
        )
        CircleButton(
            imageVector = Icons.Outlined.Share,
            label = "Share",
            contentDescription = "Share icon"
        )
        CircleButton(
            imageVector = Icons.Outlined.Report,
            label = "Report",
            contentDescription = "Report Icon",
            isImportant = true,
        )
    }
    val horizontalPadding = Modifier
        .fillMaxWidth()
        .clickable { /**/ }
        .padding(horizontal = 16.dp, vertical = 12.dp)
    Divider()
    Text(
        text = "Why you're seeing this post",
        modifier = horizontalPadding,
        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black)
    )
    Divider()
    Text(
        text = "Hide",
        modifier = horizontalPadding,
        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black)
    )
    Text(
        text = "Unfollow",
        modifier = horizontalPadding,
        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black)
    )
    Spacer(modifier = Modifier.height(64.dp))
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
