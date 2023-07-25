package com.bayu.instagramhomepage.ui.profileuser

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.R
import com.bayu.instagramhomepage.ui.components.IconButton
import com.bayu.instagramhomepage.ui.components.OutlinedButton
import com.bayu.instagramhomepage.ui.theme.colorsInstagram
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileUserScreen() {
    val pagerState = rememberPagerState()
    val listTabs = remember {
        listOf(
            Icons.Outlined.Apps,
            Icons.Outlined.SmartDisplay,
            Icons.Outlined.PlayArrow,
            Icons.Outlined.AccountBox,
        )
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ProfileUserTopBar()
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Column {
                InfoUser()
                InfoUserDescription()
            }
            InfoUserChannel()
            InfoUserContent(
                pagerState = pagerState,
                listTabs = listTabs,
                scope = scope
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoUserContent(
    pagerState: PagerState,
    listTabs: List<ImageVector>,
    scope: CoroutineScope
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        listTabs.forEachIndexed { index, imageVector ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier,
                icon = {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                },
            )
        }
    }
    HorizontalPager(
        pageCount = 4,
        state = pagerState,
    ) { page: Int ->
        when (page) {
            0 -> Collections()
            1 -> Reels()
            2 -> Streaming()
            3 -> Tags()
        }
    }
}

@Composable
fun InfoUserChannel() {
    LazyRow(
        modifier = Modifier
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
fun InfoUserDescription() {
    Column(
        modifier = Modifier
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

@Composable
fun InfoUser() {
    Row(
        modifier = Modifier
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
fun ProfileUserTopBar() {
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
        modifier = Modifier,
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
