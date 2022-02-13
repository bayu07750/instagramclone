package com.bayu.instagramhomepage.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bayu.instagramhomepage.ui.bottomnav.BottomBarScreen
import com.bayu.instagramhomepage.ui.home.HomeScreen
import com.bayu.instagramhomepage.ui.profile.ProfileScreen
import com.bayu.instagramhomepage.ui.reels.ReelsScreen
import com.bayu.instagramhomepage.ui.search.SearchScreen
import com.bayu.instagramhomepage.ui.shopping.ShoppingScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onShowBottomSheet: () -> Unit,
    onHideBottomSheet: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onShowBottomSheet = onShowBottomSheet,
                onHideBottomSheet = onHideBottomSheet,
            )
        }
        composable(route = BottomBarScreen.Search.route) {
            SearchScreen()
        }
        composable(route = BottomBarScreen.Reels.route) {
            ReelsScreen()
        }
        composable(route = BottomBarScreen.Shopping.route) {
            ShoppingScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}