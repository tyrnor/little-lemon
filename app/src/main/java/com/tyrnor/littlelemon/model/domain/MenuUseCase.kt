package com.tyrnor.littlelemon.model.domain

import com.tyrnor.littlelemon.model.data.network.MenuRepository
import com.tyrnor.littlelemon.model.data.network.response.Menu

class MenuUseCase {

    private val repository = MenuRepository()

    suspend operator fun invoke(): List<Menu>{
        return repository.getMenu()
    }
}