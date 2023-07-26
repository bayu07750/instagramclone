package com.bayu.instagramhomepage.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bayu.instagramhomepage.ui.bottomnav.BottomBarScreen
import com.bayu.instagramhomepage.ui.home.HomeScreen
import com.bayu.instagramhomepage.ui.profile.ProfileScreen
import com.bayu.instagramhomepage.ui.profileuser.ProfileUserScreen
import com.bayu.instagramhomepage.ui.reels.ReelsScreen
import com.bayu.instagramhomepage.ui.search.SearchScreen
import com.bayu.instagramhomepage.ui.shopping.ShoppingScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
    onShowBottomSheet: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                onProfileClicked = {
                    navController.navigate(BottomBarScreen.ProfileUser.route)
                },
                onShowBottomSheet = onShowBottomSheet,
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
            val isDarkMode by viewModel.isDarkMode.collectAsState()
            ProfileScreen(isDarkMode = isDarkMode, onDarkModeChanged = viewModel::setIsDarkModel)
        }
        composable(route = BottomBarScreen.ProfileUser.route) {
            ProfileUserScreen()
        }
    }
}