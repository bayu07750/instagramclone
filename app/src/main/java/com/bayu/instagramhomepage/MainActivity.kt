package com.bayu.instagramhomepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bayu.instagramhomepage.ui.HomeScreen
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
                    HomeScreen()
                }
            }
        }
    }
}
