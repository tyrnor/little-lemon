package com.tyrnor.littlelemon.model.data

import com.tyrnor.littlelemon.model.CategoryButton

class CategoryButtonList {
    val data = listOf(
        CategoryButton(title = "Starters", category = "starters", state = false),
        CategoryButton(title = "Mains", category = "mains", state = false),
        CategoryButton(title = "Desserts", category = "desserts", state = false),
        CategoryButton(title = "Drinks", category = "drinks", state = false),
    )
}