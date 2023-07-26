package com.bayu.instagramhomepage.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.components.ActionIcon
import com.bayu.instagramhomepage.ui.components.AddStory
import com.bayu.instagramhomepage.ui.components.IconButton
import com.bayu.instagramhomepage.ui.components.ToggleButton
import com.bayu.instagramhomepage.ui.theme.colorsInstagram
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Story

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onProfileClicked: () -> Unit = {},
    onShowBottomSheet: () -> Unit = {},
) {
    Column(modifier = modifier) {
        TopAppBar()
        HomeContent(
            onShowBottomSheet = onShowBottomSheet,
            onProfileClicked = onProfileClicked
        )
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
) {
    AppBar(modifier = modifier) {
        IconButton(imageVector = Icons.Outlined.AddBox)
        IconButton(imageVector = Icons.Outlined.FavoriteBorder)
        IconButton(imageVector = Icons.Outlined.ChatBubbleOutline)
    }
}

@Composable
fun HomeContent(
    onShowBottomSheet: () -> Unit,
    onProfileClicked: () -> Unit,
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
        items(20) {
            Post(
                onShowBottomSheet = onShowBottomSheet,
                onClickProfile = onProfileClicked,
            )
        }
    }
}

@Composable
fun Post(
    onShowBottomSheet: () -> Unit,
    onClickProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        PostHeader(
            onMorePostClicked = onShowBottomSheet,
            name = "Harriet Upp",
            image = painterResource(id = R.drawable.image),
            onClickProfile = onClickProfile,
        )
        PostContent(
            image = painterResource(id = R.drawable.image)
        )
        PostFooter()
    }
}

@Composable
fun PostHeader(
    name: String,
    image: Painter,
    onClickProfile: () -> Unit,
    onMorePostClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(start = 16.dp, end = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null,
                    onClick = onClickProfile
                ),
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
    PostFooterActions()
    PostFooterDescription()
}

@Composable
fun PostFooterDescription(
    modifier: Modifier = Modifier,
) {
    var isShowMore by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .animateContentSize(
                animationSpec = tween(600),
            ),
    ) {
        Text(text = "1,432 likes", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            val description = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                ) {
                    append("harriet_upp")
                }
                append(" ")
                append("is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            }
            if (isShowMore) {
                Column(
                    modifier = Modifier.weight(1F),
                ) {
                    Text(
                        text = description,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "less",
                            modifier = Modifier
                                .clickable { isShowMore = false },
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                Text(
                    text = description,
                    modifier = Modifier.weight(1F),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "more",
                        modifier = Modifier
                            .clickable { isShowMore = true },
                        fontSize = 14.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "View All 53 comments",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "2 days ago", fontSize = 12.sp)
        }
    }
}

@Composable
fun PostFooterActions(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row {
            var isButtonFavoriteChecked by remember { mutableStateOf(false) }
            ToggleButton(
                checkedImageVector = Icons.Outlined.Favorite,
                unCheckedImageVector = Icons.Outlined.FavoriteBorder,
                colorIcon = LocalContentColor.current,
                isChecked = isButtonFavoriteChecked,
                onClick = { isButtonFavoriteChecked = !isButtonFavoriteChecked }
            )
            Spacer(modifier = Modifier.width(16.dp))
            ActionIcon(imageVector = Icons.Outlined.ChatBubbleOutline)
            Spacer(modifier = Modifier.width(16.dp))
            ActionIcon(imageVector = Icons.Outlined.Send)
        }
        ActionIcon(imageVector = Icons.Outlined.BookmarkBorder)
    }
}

@Composable
fun Stories(
    modifier: Modifier = Modifier,
    items: List<Story> = remember { Data.dummyDataStories },
) {
    LazyRow(modifier = modifier) {
        item {
            AddStory()
        }
        items(items) { item ->
            Story(
                name = item.name,
                painter = painterResource(id = item.image)
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
                    .padding(5.dp),
                shape = CircleShape,
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
