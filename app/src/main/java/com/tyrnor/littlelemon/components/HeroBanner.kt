package com.tyrnor.littlelemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyrnor.littlelemon.R
import com.tyrnor.littlelemon.ui.theme.GreenLL
import com.tyrnor.littlelemon.ui.theme.YellowLL
import com.tyrnor.littlelemon.viewmodel.HomeViewModel


@Composable
fun HeroBanner(searchPhrase: String, homeViewModel: HomeViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(GreenLL),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 14.dp, top = 7.dp),
            text = "Little Lemon",
            fontSize = 40.sp,
            color = YellowLL
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(start = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "Chicago", color = Color.White, fontSize = 28.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(150.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(12)),
                    painter = painterResource(id = R.drawable.hero_img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }
        }
        TextField(
            modifier = Modifier
                .weight(1f, false)
                .fillMaxWidth()
                .padding(14.dp),
            value = searchPhrase,
            onValueChange = {
                homeViewModel.onSearchChanged(it)
                homeViewModel.filterListBySearch(it)
            },
            placeholder = { Text(text = "Enter search phrase") },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFeaeaea)),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            singleLine = true
        )
    }
}