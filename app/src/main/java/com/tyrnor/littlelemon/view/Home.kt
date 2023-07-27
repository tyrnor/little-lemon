package com.tyrnor.littlelemon.view


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tyrnor.littlelemon.model.data.network.response.Menu
import com.tyrnor.littlelemon.model.navigation.Profile
import com.tyrnor.littlelemon.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavHostController, homeViewModel: HomeViewModel) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val menu:List<Menu> by homeViewModel.menu.observeAsState(emptyList())

        Log.d("TAG", "Home: ${menu[0].title}")

        Text(text = "${menu[0].title}")

        Button(onClick = { navController.navigate(Profile.route) }) {
            Text(text = "Profile")
        }
    }
}


