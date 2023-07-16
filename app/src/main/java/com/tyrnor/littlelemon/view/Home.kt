package com.tyrnor.littlelemon.view


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.tyrnor.littlelemon.model.navigation.Profile

@Composable
fun Home(navController: NavHostController) {
    Log.d("TAG", "Home: ")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { navController.navigate(Profile.route) }) {
            Text(text = "Profile")
        }
    }
}


