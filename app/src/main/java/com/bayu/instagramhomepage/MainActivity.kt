package com.bayu.instagramhomepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
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
import androidx.navigation.compose.rememberNavController
import com.bayu.instagramhomepage.ui.bottomnav.BottomBarScreen
import com.bayu.instagramhomepage.ui.bottomnav.BottomNavGraph
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramHomePageTheme {
                val systemUiController = rememberSystemUiController()
                val isLightTheme = MaterialTheme.colors.isLight
                if (isLightTheme) {
                    systemUiController.setStatusBarColor(Color.White)
                }
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
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
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.background,
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
    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = "${screen.title} Icon"
                )
            },
            alwaysShowLabel = false,
            selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true,
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

object ClearRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}
