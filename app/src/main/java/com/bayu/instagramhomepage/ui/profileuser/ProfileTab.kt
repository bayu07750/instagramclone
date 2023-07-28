package com.bayu.instagramhomepage.ui.profileuser

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import com.bayu.instagramhomepage.R

enum class ProfileTab(
    val icon: ImageVector,
    @StringRes val label: Int,
) {
    Posts(Icons.Outlined.Apps, R.string.posts),
    Reels(Icons.Outlined.SmartDisplay, R.string.reels),
    Lives(Icons.Outlined.PlayArrow, R.string.lives),
    Account(Icons.Outlined.AccountBox, R.string.accounts)
}

val profileTabs
    @Composable
    get() = remember {
        ProfileTab.values()
    }