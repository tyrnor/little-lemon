package com.tyrnor.littlelemon.components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tyrnor.littlelemon.R
import com.tyrnor.littlelemon.model.navigation.Profile
import com.tyrnor.littlelemon.ui.theme.YellowLL


@Composable
fun Header(backArrow: Boolean, navController: NavController, profileImage: Boolean) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Icon(painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24),
            contentDescription = "",
            modifier = if (backArrow) {
                Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(70.dp)
                    .padding(10.dp)
            } else {
                Modifier
                    .size(70.dp)
                    .padding(10.dp)
            },
            tint = if (backArrow) YellowLL else Color.Transparent
        )

        Image(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.75f)
                .padding(40.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = ""
        )



        OutlinedButton(
            onClick = { navController.navigate(Profile.route) },
            enabled = profileImage,
            modifier = Modifier.size(70.dp).padding(10.dp),
            shape = CircleShape,
            border = if (profileImage) BorderStroke(1.dp, Color.Black) else BorderStroke(0.dp, Color.Transparent),
            contentPadding = PaddingValues(5.dp)

        ) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_person_24),
                contentDescription = "",
                modifier = Modifier.size(70.dp),
                tint = if (profileImage) YellowLL else Color.Transparent,
            )
        }

    }
}