package com.bayu.instagramhomepage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
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
import com.bayu.instagramhomepage.ui.home.BottomSheetContent
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme
import com.bayu.instagramhomepage.ui.utils.ClearRippleTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

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
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val navController = rememberNavController()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val isBottomSheetShow by viewModel.isBottomSheetShow.collectAsState()

    LaunchedEffect(bottomSheetState.currentValue) {
        if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            viewModel.hideBottomSheet()
        }
    }

    LaunchedEffect(isBottomSheetShow) {
        if (isBottomSheetShow) {
            bottomSheetState.show()
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Scaffold(
            bottomBar = { BottomBar(navController = navController) }
        ) {
            BottomNavGraph(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.padding(it)
            )
        }
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
