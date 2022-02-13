package com.bayu.instagramhomepage.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.bayu.instagramhomepage.R

@Composable
fun PopupPost(
    isPopupPostVisible: Boolean,
    image: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = isPopupPostVisible,
            enter = fadeIn() + expandIn(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)),
            exit = shrinkOut() + fadeOut(),
        ) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier,
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
                            text = "punitchawlofficial",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                    Image(
                        painter = rememberImagePainter(data = image) {
                            crossfade(true)
                        },
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        IconButton(
                            imageVector = Icons.Outlined.FavoriteBorder
                        )
                        IconButton(
                            imageVector = Icons.Outlined.AccountCircle
                        )
                        IconButton(
                            imageVector = Icons.Outlined.Send
                        )
                        IconButton(
                            imageVector = Icons.Outlined.MoreVert
                        )
                    }
                }
            }
        }
    }
}