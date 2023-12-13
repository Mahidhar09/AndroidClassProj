package com.example.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImagesPage() {
    val hapihapihapi = listOf(
        R.drawable.happy_family,
        R.drawable.happy_family_1,
        R.drawable.happy_family_2,
        R.drawable.happy_family_3,
        R.drawable.happy_family_4,
        R.drawable.happy_family_5,
        R.drawable.happy_family_6
        )

    Text("Images Page Content", style = MaterialTheme.typography.headlineSmall)

    LazyColumn {
        items(hapihapihapi.chunked(2)) { rowImages ->
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (hapi in rowImages) {
                    Image(
                        contentDescription = null,
                        painter = painterResource(id = hapi),
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ImagesPagePreview() {
    // You can use this preview function to see how your UI looks
    // Replace with your actual preview content
    ImagesPage()
}
