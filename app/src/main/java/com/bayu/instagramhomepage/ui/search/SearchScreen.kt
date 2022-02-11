package com.bayu.instagramhomepage.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bayu.instagramhomepage.R

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar()
        SearchContent()
    }
}

@Composable
fun ColumnScope.SearchContent() {
    LazyColumn {
        items(10) {
            Row(
                modifier = Modifier.weight(3F)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1F)
                        .heightIn(min = 120.dp, max = 120.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1F)
                        .heightIn(min = 120.dp, max = 120.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1F)
                        .heightIn(min = 120.dp, max = 120.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun SearchBar() {
    Surface(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 16.dp, bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFe2e8f0),
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Search",
                color = Color(0xFF94a3b8)
            )
        }
    }
}
