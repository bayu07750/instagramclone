package com.bayu.instagramhomepage.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Collections
import androidx.compose.material.icons.rounded.LocalMall
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

enum class TypePost(
    val imageVector: ImageVector?,
    val description: String?,
) {
    Collections(Icons.Rounded.Collections, "Collection Icon"),
    Video(Icons.Rounded.PlayArrow, "Play Icon"),
    Shopping(Icons.Rounded.LocalMall, "Local Mall Icon"),
    Photo(null, null);

    companion object {
        val Default = Photo
    }
}