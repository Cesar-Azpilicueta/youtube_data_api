package com.example.youtube_searchapi

import android.app.Activity
import android.content.Intent
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
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItemList(result: SearchItems, index: Int) {
    val context = LocalContext.current
    val title = specialCharConversion(result.snippet.title)
    val description = result.snippet.description
    val thumbnails = result.snippet.thumbnails.default.url
    val videoId = result.id.videoId
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            Toast.makeText(context, "card $index", Toast.LENGTH_SHORT).show()
            val intent = Intent(context,ViewActivity::class.java)
            intent.putExtra("videoId",videoId)
            startActivity(context,intent,null)
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

fun specialCharConversion(text: String): String {
    var beforeText: String = ""
    var decimalNum = ""
    var specialCase: String = ""
    var checkPoint: Int = 0

    if (text.contains("&#")) {
        checkPoint = 1
        beforeText = text.substring(text.indexOf("&"), text.indexOf(";") + 1)
        decimalNum = beforeText.substring(beforeText.indexOf("#") + 1, beforeText.indexOf(";"))
    } else if (text.contains("&")) {
        checkPoint = 2
        beforeText = text.substring(text.indexOf("&"), text.indexOf(";") + 1)
        println("checkPoint 2 $beforeText")
        when (beforeText) {
            "&nbsp;" -> specialCase = text.replace("&nbsp;", " ")
            "&lt;" -> specialCase = text.replace("&lt;", "<")
            "&gt;" -> specialCase = text.replace("&gt;", ">")
            "&amp;" -> specialCase = text.replace("&amp;", "&")
            "&quot;" -> specialCase = text.replace("&quot;", "\"")
        }
    }
    val afterText: Char = if (decimalNum != "") {
        decimalNum.toInt().toChar()
    } else {
        'a'
    }

    return when (checkPoint) {
        1 -> text.replace(beforeText, afterText.toString())
        2 -> specialCase
        else -> text
    }
}