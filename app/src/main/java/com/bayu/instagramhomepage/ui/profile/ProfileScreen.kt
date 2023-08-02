package com.bayu.instagramhomepage.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.ui.components.FilledButton
import com.bayu.instagramhomepage.ui.profileuser.InfoUser
import com.bayu.instagramhomepage.ui.profileuser.InfoUserDescription
import com.bayu.instagramhomepage.ui.theme.InstagramHomePageTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewProfileScreen() {
    InstagramHomePageTheme {
        ProfileScreen(
            isDarkMode = false,
            onDarkModeChanged = {}
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    isDarkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetContent = {
            val horizontalPaddingMod = Modifier
                .padding(horizontal = 16.dp)

            Surface(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .widthIn(min = 40.dp)
                    .heightIn(min = 4.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                color = Color.Gray,
                shape = RoundedCornerShape(30.dp),
            ) { }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = horizontalPaddingMod
                    .fillMaxWidth(),
            ) {
                Text(text = "Dark Mode", modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                Switch(checked = isDarkMode, onCheckedChange = onDarkModeChanged)
            }
            Spacer(modifier = Modifier.height(16.dp))
        },
        sheetState = bottomSheetState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "johnDoe11".split("").joinToString("", limit = 10, truncated = ""))
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                        }
                    },
                    actions = {
                        val tintIcon = MaterialTheme.colors.onBackground
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = tintIcon)
                        }
                        IconButton(onClick = {
                            scope.launch {
                                bottomSheetState.show()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = tintIcon)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 0.dp,
                )
            }
        ) { innerPadding ->
            val innerMod = Modifier.padding(innerPadding)
            LazyColumn(
                modifier = innerMod,
            ) {
                item {
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        InfoUser()
                        InfoUserDescription {
                            FilledButton(
                                text = "Edit Profile",
                                modifier = Modifier.weight(1F)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            FilledButton(
                                text = "Share Profile",
                                modifier = Modifier.weight(1F)
                            )
                        }
                    }
                }
            }
        }
    }
}