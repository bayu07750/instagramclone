package com.bayu.instagramhomepage.ui.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomBarScreen(
        route = "home",
        title = "home",
        icon = Icons.Rounded.Home
    )

    object Search : BottomBarScreen(
        route = "search",
        title = "search",
        icon = Icons.Rounded.Search
    )

    object Reels : BottomBarScreen(
        route = "reals",
        title = "reals",
        icon = Icons.Outlined.SmartDisplay
    )

    object Shopping : BottomBarScreen(
        route = "shopping",
        title = "shopping",
        icon = Icons.Outlined.ShoppingBag,
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "profile",
        icon = Icons.Outlined.AccountCircle,
    )

}
