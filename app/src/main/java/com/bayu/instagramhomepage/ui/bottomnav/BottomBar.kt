package com.bayu.instagramhomepage.ui.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavHostController,
    setStatusBarColor: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Reels,
        BottomBarScreen.Shopping,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isReelsScreenDestination = currentDestination?.route == BottomBarScreen.Reels.route

    if (isReelsScreenDestination) {
        setStatusBarColor(Color.Black)
    } else {
        setStatusBarColor(MaterialTheme.colors.background)
    }

    BottomNavigation(
        modifier = modifier,
        backgroundColor = if (isReelsScreenDestination) Color.Black else MaterialTheme.colors.background,
        contentColor = if (isReelsScreenDestination) Color.White else MaterialTheme.colors.onBackground,
        elevation = 0.dp,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = if (selected) screen.selectedIcon else screen.unSelectedIcon,
                contentDescription = "${screen.title} Icon"
            )
        },
        alwaysShowLabel = false,
        selected = selected,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}