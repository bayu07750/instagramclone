package com.bayu.instagramhomepage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bayu.instagramhomepage.ui.bottomnav.BottomBar
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

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
                    MainScreen {
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
    setStatusBarColor: (Color) -> Unit,
) {
    val navController = rememberNavController()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    setStatusBarColor = setStatusBarColor
                )
            }
        ) {
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(it),
                onShowBottomSheet = { scope.launch { bottomSheetState.show() } },
                onHideBottomSheet = { scope.launch { bottomSheetState.hide() } }
            )
        }
    }
}
