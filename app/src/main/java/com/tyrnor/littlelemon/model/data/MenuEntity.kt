package com.tyrnor.littlelemon.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MenuEntity")
data class MenuEntity(
    val category: String,
    val description: String,
    @PrimaryKey val id: Int,
    val image: String,
    val price: String,
    val title: String
)
