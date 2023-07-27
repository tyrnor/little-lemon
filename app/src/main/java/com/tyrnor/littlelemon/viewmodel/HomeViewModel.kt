package com.tyrnor.littlelemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyrnor.littlelemon.model.data.network.response.Menu
import com.tyrnor.littlelemon.model.domain.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _menu = MutableLiveData<List<Menu>>()
    val menu : LiveData<List<Menu>> = _menu

    private val menuUseCase = MenuUseCase()

    init {
        viewModelScope.launch {
            _menu.value = getMenu() ?: emptyList()
        }
    }


    suspend fun getMenu(): List<Menu> {
        return menuUseCase()
    }
}