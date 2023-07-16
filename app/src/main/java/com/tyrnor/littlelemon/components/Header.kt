package com.tyrnor.littlelemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tyrnor.littlelemon.R
import com.tyrnor.littlelemon.ui.theme.GreenLL
import com.tyrnor.littlelemon.ui.theme.YellowLL


@Composable
fun Header(backArrow: Boolean, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)

    ) {
        if (backArrow) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .height(60.dp)
                    .width(60.dp)
                    .padding(top = 16.dp, start = 5.dp),
                tint = YellowLL
            )
        }
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "")
    }
}