package com.bayu.instagramhomepage.ui.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bayu.instagramhomepage.ui.utils.ClearRippleTheme

@Composable
fun BottomBar(
    navController: NavHostController,
    setStatusBarColor: (Color) -> Unit,
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
        modifier = Modifier,
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

    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
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
}