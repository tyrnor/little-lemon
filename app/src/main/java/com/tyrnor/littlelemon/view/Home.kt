package com.tyrnor.littlelemon.view


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.tyrnor.littlelemon.components.Header
import com.tyrnor.littlelemon.components.HeroBanner
import com.tyrnor.littlelemon.components.MenuList
import com.tyrnor.littlelemon.model.CategoryButton
import com.tyrnor.littlelemon.model.data.MenuEntity
import com.tyrnor.littlelemon.viewmodel.HomeViewModel

@Composable
fun Home(navController: NavHostController, homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val menu: List<MenuEntity> by homeViewModel.menu.observeAsState(emptyList())
        val filteredMenu: List<MenuEntity> by homeViewModel.filteredMenu.observeAsState(menu)
        val btnList: List<CategoryButton> by homeViewModel.categoryButtons.observeAsState(emptyList())
        val searchPhrase: String by homeViewModel.searchPhrase.observeAsState("")


        Header(backArrow = false, navController = navController, profileImage = true)
        HeroBanner(searchPhrase = searchPhrase, homeViewModel= homeViewModel)
        MenuList(menuItems = menu, filteredMenu = filteredMenu, btnList = btnList, homeViewModel = homeViewModel)
    }
}







