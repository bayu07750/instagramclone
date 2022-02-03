package com.bayu.instagramhomepage.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.ui.home.CircleButton

@Composable
fun ColumnScope.BottomSheetContent() {
    Surface(
        modifier = Modifier
            .padding(top = 12.dp)
            .widthIn(min = 60.dp)
            .heightIn(min = 4.dp)
            .align(alignment = Alignment.CenterHorizontally),
        color = Color.Gray,
        shape = RoundedCornerShape(30.dp),
    ) { }
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        CircleButton(
            imageVector = Icons.Outlined.Link,
            label = "Link",
            contentDescription = "Link icon"
        )
        CircleButton(
            imageVector = Icons.Outlined.Share,
            label = "Share",
            contentDescription = "Share icon"
        )
        CircleButton(
            imageVector = Icons.Outlined.Report,
            label = "Report",
            contentDescription = "Report Icon",
            isImportant = true,
        )
    }
    val horizontalPadding = Modifier
        .fillMaxWidth()
        .clickable { /**/ }
        .padding(horizontal = 16.dp, vertical = 12.dp)
    Divider()
    Text(
        text = "Why you're seeing this post",
        modifier = horizontalPadding,
        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black)
    )
    Divider()
    Text(
        text = "Hide",
        modifier = horizontalPadding,
        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black)
    )
    Text(
        text = "Unfollow",
        modifier = horizontalPadding,
        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black)
    )
    Spacer(modifier = Modifier.height(64.dp))
}