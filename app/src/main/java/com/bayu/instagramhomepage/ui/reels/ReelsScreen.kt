package com.bayu.instagramhomepage.ui.reels

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bayu.instagramhomepage.ui.components.ActionIcon
import com.bayu.instagramhomepage.ui.components.ToggleButton
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Reel

@Composable
fun ReelsScreen(
    reels: List<Reel> = remember {
        Data.dummyDataReels
    },
) {
    Reels(items = reels)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Reels(
    items: List<Reel>
) {
    val pagerState = rememberPagerState()

    VerticalPager(
        state = pagerState,
        pageCount = items.size,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        key = { it },
        beyondBoundsPageCount = 1,
    ) { index ->
        val reel = items[index]

        Reel(index, reel, pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Reel(
    index: Int,
    item: Reel,
    pagerState: PagerState,
) {
    Box {
        Player(
            index = index,
            uri = item.getUriVideo(),
            pagerState = pagerState,
        )
        Surface(
            color = Color.Transparent,
            contentColor = Color.White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                var isShowMoreDescription by remember {
                    mutableStateOf(false)
                }

                ReelInfo(
                    item = item,
                    isShowMoreDescription = isShowMoreDescription,
                    onIsShowMoreDescriptionChange = {
                        isShowMoreDescription = !isShowMoreDescription
                    }
                )
                ReelActions(item)
            }
        }
    }
}

@Composable
fun ReelActions(item: Reel) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, end = 12.dp, bottom = 12.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = CenterHorizontally,
    ) {
        ActionIcon(imageVector = Icons.Outlined.PhotoCamera)
        Column(
            horizontalAlignment = CenterHorizontally,
        ) {
            Column {
                var isButtonFavoriteChecked by remember { mutableStateOf(false) }
                ToggleButton(
                    checkedImageVector = Icons.Outlined.Favorite,
                    unCheckedImageVector = Icons.Outlined.FavoriteBorder,
                    colorIcon = LocalContentColor.current,
                    isChecked = isButtonFavoriteChecked,
                    onClick = { isButtonFavoriteChecked = !isButtonFavoriteChecked }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.likes,
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.body2,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                ActionIcon(imageVector = Icons.Outlined.ModeComment)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.comment,
                    modifier = Modifier.align(CenterHorizontally),
                    style = MaterialTheme.typography.body2,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ActionIcon(imageVector = Icons.Outlined.Send)
            Spacer(modifier = Modifier.height(16.dp))
            ActionIcon(imageVector = Icons.Outlined.MoreVert)
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.White),
            ) {
                Image(
                    painter = rememberImagePainter(data = item.music.profile),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun RowScope.ReelInfo(
    item: Reel,
    isShowMoreDescription: Boolean,
    onIsShowMoreDescriptionChange: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(start = 12.dp, bottom = 12.dp, end = 32.dp, top = 24.dp)
            .weight(1F)
            .animateContentSize(
                animationSpec = tween(400)
            ),
    ) {
        ReelInfoUser(
            item = item
        )
        Spacer(modifier = Modifier.heightIn(16.dp))
        ReelInfoDescription(
            item = item,
            isShowMoreDescription = isShowMoreDescription,
            onIsShowMoreDescriptionChange = onIsShowMoreDescriptionChange,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ReelInfoMusic(item)
    }
}

@Composable
fun ReelInfoMusic(item: Reel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Rounded.GraphicEq,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = item.music.name,
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Surface(
            modifier = Modifier
                .size(2.dp),
            shape = CircleShape,
            color = Color.White,
        ) { }
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = if (item.music.isOriginalAudio) "Original Audio" else item.music.author,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun ReelInfoDescription(
    item: Reel,
    isShowMoreDescription: Boolean,
    onIsShowMoreDescriptionChange: () -> Unit,
) {
    val description = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.subtitle1.toSpanStyle()) {
            append(item.description)
        }
        append("  ")
        withStyle(
            style = MaterialTheme.typography.body2.copy(
                color = Color.White.copy(
                    alpha = .6F
                )
            ).toSpanStyle()
        ) {
            val tags = item.tags.joinToString(" ")
            append(tags)
        }
    }

    Text(
        text = description,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onIsShowMoreDescriptionChange.invoke()
            },
        maxLines = if (isShowMoreDescription) Int.MAX_VALUE else 1,
        overflow = if (isShowMoreDescription) TextOverflow.Clip else TextOverflow.Ellipsis
    )
}

@Composable
fun ReelInfoUser(item: Reel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            shape = CircleShape,
        ) {
            Image(
                painter = rememberImagePainter(data = item.user.profile),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.user.username,
            style = MaterialTheme.typography.subtitle2.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.Transparent,
            contentColor = Color.White,
            border = BorderStroke(1.dp, Color.White),
        ) {
            Text(
                text = if (item.user.follow) "Unfollow" else "Follow",
                modifier = Modifier
                    .padding(2.dp)
                    .padding(horizontal = 6.dp),
                style = MaterialTheme.typography.body2,
            )
        }
    }
}