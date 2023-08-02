package com.bayu.instagramhomepage.ui.profileuser

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.components.IconButton
import com.bayu.instagramhomepage.ui.components.OutlinedButton
import com.bayu.instagramhomepage.ui.theme.colorsInstagram
import com.bayu.instagramhomepage.ui.utils.Data
import com.bayu.instagramhomepage.ui.utils.Post


@Preview
@Composable
fun ProfileUserScreenPreview() {
    ProfileUserScreen()
}

@Composable
fun ProfileUserScreen(
    modifier: Modifier = Modifier,
    posts: List<Post> = remember { Data.dummyDataPosts },
) {
    val lazyGridState = rememberLazyGridState()
    var currentPage by remember { mutableStateOf(ProfileTab.Posts) }

    Scaffold(
        modifier = modifier,
        topBar = {
            ProfileUserTopBar()
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            state = lazyGridState,
            modifier = Modifier.padding(innerPadding),
        ) {
            header {
                Column {
                    InfoUser()
                    InfoUserDescription {
                        OutlinedButton(
                            text = "Follow",
                            modifier = Modifier.weight(1F)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        OutlinedButton(
                            text = "Message",
                            modifier = Modifier.weight(1F)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        OutlinedButton(
                            text = "Email",
                            modifier = Modifier.weight(1F)
                        )
                    }
                }
            }

            header {
                InfoUserChannel()
            }

            header {
                InfoUserContent(
                    listTabs = profileTabs,
                    currentPage = currentPage,
                    onItemTabClicked = { selectedTab ->
                        currentPage = selectedTab
                    },
                )
            }
            when (currentPage) {
                ProfileTab.Posts -> {
                    postItems(items = posts)
                }

                ProfileTab.Reels -> {
                    header {
                        Reels()
                    }
                }

                ProfileTab.Lives -> {
                    header {
                        Streaming()
                    }
                }

                ProfileTab.Account -> {
                    header {
                        Tags()
                    }
                }
            }
        }
    }
}

@Composable
fun InfoUserContent(
    listTabs: Array<ProfileTab>,
    currentPage: ProfileTab,
    onItemTabClicked: (ProfileTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    TabRow(
        selectedTabIndex = listTabs.indexOf(currentPage),
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        listTabs.forEachIndexed { index, tab ->
            Tab(
                selected = listTabs.indexOf(currentPage) == index,
                onClick = {
                    onItemTabClicked.invoke(tab)
                },
                modifier = Modifier,
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = stringResource(id = tab.label),
                        modifier = Modifier.size(28.dp)
                    )
                },
            )
        }
    }
}

@Composable
fun InfoUserChannel(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .padding(vertical = 20.dp)
    ) {
        items(6) { index ->
            Column(
                modifier = Modifier
                    .padding(start = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Surface(
                    modifier = Modifier,
                    shape = CircleShape,
                    color = MaterialTheme.colors.background,
                    border = BorderStroke(
                        width = 2.dp,
                        brush = Brush.verticalGradient(colorsInstagram)
                    ),
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(4.dp),
                        shape = CircleShape,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(65.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Channel $index",
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
fun InfoUserDescription(modifier: Modifier = Modifier, actions: @Composable RowScope.() -> Unit) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
    ) {
        val description = buildAnnotatedString {
            withStyle(MaterialTheme.typography.body2.toSpanStyle()) {
                append("Programming Mentor")
                append("\n")
                append("15 years of coding experience 💻")
                append("\n")
                append("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout 🥰")
                append("\n")
                append("⬇️ Follow me for useful tips about jetpack compose everyday")
                append("\n")
                append("Thanks")
            }
        }
        Text(
            text = description,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            actions.invoke(this)
        }
    }
}

@Composable
fun InfoUser(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(top = 8.dp, bottom = 12.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier,
            shape = CircleShape,
            color = MaterialTheme.colors.background,
            border = BorderStroke(
                width = 2.dp,
                brush = Brush.verticalGradient(colorsInstagram)
            ),
        ) {
            Surface(
                modifier = Modifier
                    .padding(4.dp),
                shape = CircleShape,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.width(32.dp))
        Row(
            modifier = Modifier
                .weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemInfoUser(
                text = "780",
                label = "Posts",
            )
            ItemInfoUser(
                text = "99.7K",
                label = "Followers",
            )
            ItemInfoUser(
                text = "456",
                label = "Following",
            )
        }
    }
}

@Composable
fun ProfileUserTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Harriet Upp",
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onBackground
                )
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(imageVector = Icons.Rounded.ArrowBack)
        },
        actions = {
            IconButton(imageVector = Icons.Outlined.Notifications)
            IconButton(imageVector = Icons.Rounded.MoreVert)
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp,
    )
}
