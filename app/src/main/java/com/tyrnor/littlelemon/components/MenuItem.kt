package com.tyrnor.littlelemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tyrnor.littlelemon.model.data.MenuEntity

@Composable
fun MenuItem(menuItem: MenuEntity) {

    val roundOff = String.format("%.2f", menuItem.price.toFloat())
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .background(Color.LightGray)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(14.dp)
            .background(Color.White)
    ) {
        Column(
            Modifier
                .fillMaxWidth(0.75f)
                .padding(end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = menuItem.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                text = menuItem.description,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            Text(
                text = "$${roundOff}",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Column(modifier = Modifier) {
            GlideImage(
                imageModel = { menuItem.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
            )
        }
    }
}