package com.bayu.instagramhomepage.ui.reels

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Reel
import com.bayu.instagramhomepage.ui.utils.VideoPlayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReelsScreen(
    reels: List<Reel> = Data.dummyDataReels,
) {

    VerticalPager(
        count = reels.size,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) { index ->
        val reel = reels[index]

        Box {
            VideoPlayer(uri = reel.getUriVideo())
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

                    Column(
                        modifier = Modifier
                            .padding(start = 12.dp, bottom = 12.dp, end = 32.dp, top = 24.dp)
                            .weight(1F)
                            .animateContentSize(
                                animationSpec = tween(400)
                            ),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Surface(
                                shape = CircleShape,
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = reel.user.profile),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = reel.user.username,
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
                                    text = if (reel.user.follow) "Unfollow" else "Follow",
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .padding(horizontal = 6.dp),
                                    style = MaterialTheme.typography.body2,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.heightIn(16.dp))

                        val description = buildAnnotatedString {
                            withStyle(style = MaterialTheme.typography.subtitle1.toSpanStyle()) {
                                append(reel.description)
                            }
                            append("  ")
                            withStyle(
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.White.copy(
                                        alpha = .6F
                                    )
                                ).toSpanStyle()
                            ) {
                                val tags = reel.tags.joinToString(" ")
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
                                    isShowMoreDescription = !isShowMoreDescription
                                },
                            maxLines = if (isShowMoreDescription) Int.MAX_VALUE else 1,
                            overflow = if (isShowMoreDescription) TextOverflow.Clip else TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(16.dp))
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
                                text = reel.music.name,
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
                                text = if (reel.music.isOriginalAudio) "Original Audio" else reel.music.author,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp, end = 12.dp, bottom = 12.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = CenterHorizontally,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PhotoCamera,
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp),
                        )
                        Column(
                            horizontalAlignment = CenterHorizontally,
                        ) {
                            Column {
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp),
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = reel.likes,
                                    modifier = Modifier.align(CenterHorizontally)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Column {
                                Icon(
                                    imageVector = Icons.Outlined.ModeComment,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp),
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = reel.comment,
                                    modifier = Modifier.align(CenterHorizontally)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Icon(
                                imageVector = Icons.Outlined.Send,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Surface(
                                modifier = Modifier,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(2.dp, Color.White),
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = reel.music.profile),
                                    contentDescription = null,
                                    modifier = Modifier.size(35.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}