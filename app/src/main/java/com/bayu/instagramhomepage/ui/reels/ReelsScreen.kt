package com.bayu.instagramhomepage.ui.reels

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.utils.VideoPlayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReelsScreen() {
    val reels = remember {
        listOf(
            Uri.parse("asset:///sd_1.mp4"),
            Uri.parse("asset:///sd_2.mp4"),
        )
    }

    VerticalPager(
        count = reels.size,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) { index ->
        val reel = reels[index]

        Box {
            VideoPlayer(uri = reel)
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
                    Column(
                        modifier = Modifier
                            .padding(start = 12.dp, bottom = 12.dp, end = 32.dp, top = 24.dp)
                            .weight(1F),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Surface(
                                shape = CircleShape,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Kya_fry",
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
                                    text = "Follow",
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
                                append("My Happy Baby")
                            }
                            append("  ")
                            withStyle(
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.White.copy(
                                        alpha = .6F
                                    )
                                ).toSpanStyle()
                            ) {
                                append("#hellowlrd #game #music #horror")
                            }
                        }

                        Text(
                            text = description,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
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
                                text = "kye_fry",
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
                                text = "Original Audio",
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
                                    text = "19.1K",
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
                                    text = "106",
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
                                    painter = painterResource(id = R.drawable.image),
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