package com.tyrnor.littlelemon.components

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyrnor.littlelemon.model.CategoryButton
import com.tyrnor.littlelemon.model.data.MenuEntity
import com.tyrnor.littlelemon.ui.theme.GreenLL
import com.tyrnor.littlelemon.ui.theme.YellowLL
import com.tyrnor.littlelemon.viewmodel.HomeViewModel

@Composable
fun MenuList(menuItems: List<MenuEntity>, filteredMenu: List<MenuEntity>, btnList: List<CategoryButton>, homeViewModel: HomeViewModel) {
    if (menuItems.isNotEmpty()) {
        val color = remember {
            mutableStateOf(true)
        }
        val scrollState = rememberScrollState()

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, top = 20.dp, bottom = 15.dp),
                text = "ORDER FOR DELIVERY!",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                btnList.forEach { btn ->
                    Button(
                        onClick = {
                            color.value = false
                            color.value = true
                            btn.state = !btn.state
                            if (btn.state) {
                                btnList.filter {
                                    it.category != btn.category
                                }.forEach {
                                    it.state = false
                                }
                                homeViewModel.filterListByCategory(menuItems.filter { it.category == btn.category })
                            } else {
                                homeViewModel.filterListByCategory(menuItems)
                            }

                        }, colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (btn.state) {
                                YellowLL
                            } else {
                                Color(0xFFedefee)
                            },
                            contentColor = GreenLL
                        ),
                        shape = RoundedCornerShape(40),
                        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
                    ) {
                        Text(text = btn.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (filteredMenu.isNotEmpty()) {
            LazyColumn {
                items(filteredMenu) { menuItem ->
                    MenuItem(menuItem = menuItem)
                }
            }
        } else {
            Text(
                text = "There are no items of that category",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Text(text = "Sorry :)", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
    } else {
        Text(text = "Can't load menu list")
    }

}