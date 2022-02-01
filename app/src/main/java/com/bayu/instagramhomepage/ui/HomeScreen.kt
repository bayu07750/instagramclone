package com.bayu.instagramhomepage.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.R

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier,
                color = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_instagram_logo),
                        contentDescription = stringResource(R.string.desc_instagram_logo),
                    )
                    Row {
                        HomeButton(imageVector = Icons.Outlined.AddBox)
                        HomeButton(imageVector = Icons.Outlined.FavoriteBorder)
                        HomeButton(imageVector = Icons.Outlined.ChatBubbleOutline)
                    }
                }
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            item {
                Stories()
            }
            items(5) {
                Post()
            }
        }
    }
}

@Composable
fun Post() {
    Column(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .padding(start = 16.dp, end = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    modifier = Modifier
                        .size(35.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF515BD4),
                                Color(0xFF8134AF),
                                Color(0xFFDD2A7B),
                                Color(0xFFFEDA77),
                                Color(0xFFF58529),
                            )
                        )
                    ),
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
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "stevdza_san",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
                )
            }
            HomeButton(imageVector = Icons.Outlined.MoreVert)
        }
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
        )
//        Row(
//            modifier = Modifier
//                .padding(top = 0.dp, start = 2.dp, bottom = 12.dp, end = 2.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                HomeButton(imageVector = Icons.Outlined.FavoriteBorder)
//                HomeButton(imageVector = Icons.Outlined.ModeComment)
//                HomeButton(imageVector = Icons.Outlined.Send)
//            }
//            HomeButton(imageVector = Icons.Outlined.TurnedInNot)
//        }
    }
}

@Composable
fun Stories() {
    LazyRow {
        item {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            ) {
                Box {
                    Surface(
                        modifier = Modifier
                            .size(75.dp),
                        shape = CircleShape,
                        color = Color(0xFFf8fafc),
                        border = BorderStroke(2.dp, Color.LightGray),
                    ) {}
                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        shape = CircleShape,
                        color = Color(0xFF3b82f6),
                        contentColor = Color.White,
                        border = BorderStroke(2.dp, Color.White),
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
        items(10) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .size(75.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF515BD4),
                                Color(0xFF8134AF),
                                Color(0xFFDD2A7B),
                                Color(0xFFFEDA77),
                                Color(0xFFF58529),
                            )
                        )
                    )
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(6.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.LightGray),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                LabelStory(text = "neojapan_")
            }
        }
    }
}

@Composable
fun ColumnScope.LabelStory(
    text: String,
) {
    Text(
        text = text,
        modifier = Modifier
            .align(alignment = CenterHorizontally),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body2.copy(color = Color.Black)
    )
}

@Composable
fun HomeButton(
    imageVector: ImageVector,
) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}