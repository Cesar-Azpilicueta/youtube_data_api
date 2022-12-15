package com.example.youtube_searchapi

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItemList(result: SearchItems, index: Int) {
    val context = LocalContext.current
    val title = result.snippet.title
    val description = result.snippet.description
    val thumbnails = result.snippet.thumbnails.default.url
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            Toast.makeText(context, "card $index", Toast.LENGTH_SHORT).show()
        }

    ) {
        Row {
            Thumbnails(thumbnails)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = title, style = MaterialTheme.typography.bodyMedium)
                Text(text = description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Thumbnails(image: String) {
    val context = LocalContext.current
    GlideImage(
        model = image, contentDescription = "", modifier = Modifier
            .padding(8.dp)
            .size(90.dp, 90.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))

    ) {
        it.thumbnail()
            .centerCrop()
    }
}