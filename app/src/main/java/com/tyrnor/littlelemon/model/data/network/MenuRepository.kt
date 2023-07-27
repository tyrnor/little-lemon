package com.tyrnor.littlelemon.model.data.network


import com.tyrnor.littlelemon.model.data.network.response.Menu

class MenuRepository {
    private val api = MenuService()

    suspend fun getMenu(): List<Menu>{
        return api.getMenu()
    }
}