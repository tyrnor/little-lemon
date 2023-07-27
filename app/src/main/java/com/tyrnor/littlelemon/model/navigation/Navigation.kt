package com.tyrnor.littlelemon.model.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tyrnor.littlelemon.model.data.DataStoreManager
import com.tyrnor.littlelemon.view.Home
import com.tyrnor.littlelemon.view.Onboarding
import com.tyrnor.littlelemon.view.Profile
import com.tyrnor.littlelemon.view.Splash
import com.tyrnor.littlelemon.viewmodel.HomeViewModel
import com.tyrnor.littlelemon.viewmodel.OnboardingViewModel
import com.tyrnor.littlelemon.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Navigation(
    onboardingViewModel: OnboardingViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    dataStoreManager: DataStoreManager = DataStoreManager(
        LocalContext.current),
) {

    val navController = rememberNavController()

    val coroutineScope = rememberCoroutineScope()

    var startDestination by remember {
        mutableStateOf(Splash.route)
    }

    LaunchedEffect(true) {
        delay(700)
        coroutineScope.launch {
            //dataStoreManager.clearDataStore()
            dataStoreManager.getFromDataStore().collect {
                startDestination =
                    if (it.email.isNotEmpty() && it.firstName.isNotEmpty() && it.lastName.isNotEmpty()) {
                        Home.route
                    } else {
                        Onboarding.route
                    }
            }
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Splash.route) {
            Splash()
        }
        composable(Onboarding.route) {
            Onboarding(navController, onboardingViewModel, dataStoreManager)
        }
        composable(Home.route) {
            Home(navController, homeViewModel)
        }
        composable(Profile.route) {
            Profile(navController,profileViewModel, dataStoreManager)
        }
    }
}