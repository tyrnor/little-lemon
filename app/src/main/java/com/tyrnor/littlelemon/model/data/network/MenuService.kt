package com.tyrnor.littlelemon.model.data.network

import com.tyrnor.littlelemon.model.data.network.clients.MenuClient
import com.tyrnor.littlelemon.model.data.network.response.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMenu(): List<Menu>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MenuClient::class.java).getMenu()
            response.body()?.menu ?: emptyList()
        }
    }
}