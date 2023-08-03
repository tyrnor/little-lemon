package com.tyrnor.littlelemon.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuEntity")
    fun getAllMenu(): List<MenuEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMenu(menuEntity: MenuEntity)
}