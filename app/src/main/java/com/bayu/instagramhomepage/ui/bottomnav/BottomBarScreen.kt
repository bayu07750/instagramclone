package com.bayu.instagramhomepage.ui.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
) {

    object Home : BottomBarScreen(
        route = "home",
        title = "home",
        selectedIcon = Icons.Rounded.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    object Search : BottomBarScreen(
        route = "search",
        title = "search",
        selectedIcon = Icons.Rounded.Search,
        unSelectedIcon = Icons.Outlined.Search
    )

    object Reels : BottomBarScreen(
        route = "reals",
        title = "reals",
        selectedIcon = Icons.Rounded.SmartDisplay,
        unSelectedIcon = Icons.Outlined.SmartDisplay
    )

    object Shopping : BottomBarScreen(
        route = "shopping",
        title = "shopping",
        selectedIcon = Icons.Rounded.ShoppingBag,
        unSelectedIcon = Icons.Outlined.ShoppingBag
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "profile",
        selectedIcon = Icons.Rounded.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    )

}
