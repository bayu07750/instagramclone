package com.bayu.instagramhomepage.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.ui.MainViewModel

@Composable
fun ProfileScreen(
    viewModel: MainViewModel
) {
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(checked = isDarkMode, onCheckedChange = viewModel::setIsDarkModel)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Dark Mode", textAlign = TextAlign.Center)
    }
}