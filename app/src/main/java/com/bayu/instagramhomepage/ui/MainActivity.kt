package com.bayu.instagramhomepage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bayu.instagramhomepage.ui.bottomnav.BottomBar
import com.bayu.instagramhomepage.ui.bottomnav.BottomNavGraph
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme
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
                    MainScreen(viewModel) {
                        systemUiController.setStatusBarColor(it)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    setStatusBarColor: (Color) -> Unit,
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
            bottomBar = { BottomBar(navController = navController, setStatusBarColor = setStatusBarColor) }
        ) {
            BottomNavGraph(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.padding(it)
            )
        }
    }
}
