package com.tyrnor.littlelemon.model.data.network.clients

import com.tyrnor.littlelemon.model.data.network.response.MenuResponse
import retrofit2.Response
import retrofit2.http.GET

interface MenuClient {
    @GET("/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
    suspend fun getMenu(): Response<MenuResponse>
}