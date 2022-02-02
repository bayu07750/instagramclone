package com.bayu.instagramhomepage.ui.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bayu.instagramhomepage.ui.home.HomeScreen
import com.bayu.instagramhomepage.ui.profile.ProfileScreen
import com.bayu.instagramhomepage.ui.reels.ReelsScreen
import com.bayu.instagramhomepage.ui.search.SearchScreen
import com.bayu.instagramhomepage.ui.shopping.ShoppingScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
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