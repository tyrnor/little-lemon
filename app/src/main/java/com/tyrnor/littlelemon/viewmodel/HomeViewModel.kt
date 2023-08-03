package com.tyrnor.littlelemon.viewmodel

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyrnor.littlelemon.LittleLemonApp
import com.tyrnor.littlelemon.MainActivity
import com.tyrnor.littlelemon.model.CategoryButton
import com.tyrnor.littlelemon.model.data.CategoryButtonList
import com.tyrnor.littlelemon.model.data.MenuEntity
import com.tyrnor.littlelemon.model.data.getDatabase
import com.tyrnor.littlelemon.model.data.network.response.Menu
import com.tyrnor.littlelemon.model.domain.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _menu = MutableLiveData<List<MenuEntity>>()
    val menu: LiveData<List<MenuEntity>> = _menu

    private val _filteredMenu = MutableLiveData<List<MenuEntity>>()
    val filteredMenu: LiveData<List<MenuEntity>> = _filteredMenu

    private val _categoryButtons = MutableLiveData<List<CategoryButton>>()
    val categoryButtons: LiveData<List<CategoryButton>> = _categoryButtons

    private val _searchPhrase = MutableLiveData<String>()
    val searchPhrase: LiveData<String> = _searchPhrase



    private val menuUseCase = MenuUseCase()

    init {
        viewModelScope.launch {
            if (getMenuFromAPI().isNotEmpty()){
                getMenuFromAPI().forEach { menu ->
                    insertMenuData(
                        MenuEntity(
                            menu.category,
                            menu.description,
                            menu.id,
                            menu.image,
                            menu.price,
                            menu.title
                        )
                    )
                }
            }
            getMenu()
            getCategoryButtons()
        }

    }


    private suspend fun getMenuFromAPI(): List<Menu> {
        return menuUseCase()
    }

    private fun getMenu(){
        var menuList: List<MenuEntity>
        viewModelScope.launch {
            menuList = withContext(Dispatchers.IO){
                getDatabase(context = LittleLemonApp.applicationContext()).menuDao().getAllMenu()
            }
            _menu.value = menuList
        }
    }

    private fun insertMenuData(model: MenuEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                getDatabase(context = LittleLemonApp.applicationContext())
                    .menuDao()
                    .insertMenu(model)
            }
        }
    }

    private fun getCategoryButtons(){
        _categoryButtons.value = CategoryButtonList().data
    }

    fun onSearchChanged(searchPhrase: String){
        _searchPhrase.value = searchPhrase
    }

    fun filterListBySearch(searchPhrase: String){
        if (searchPhrase.isEmpty()){
            _filteredMenu.value = _menu.value
        } else{
            _filteredMenu.value = _menu.value?.filter { it.title.contains(searchPhrase,ignoreCase = true) }
        }
    }
    fun filterListByCategory(filteredMenu: List<MenuEntity>){
        _filteredMenu.value = filteredMenu
    }
}