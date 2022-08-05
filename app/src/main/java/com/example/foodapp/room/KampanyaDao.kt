package com.example.foodapp.room

import androidx.room.Dao
import androidx.room.Query
import com.example.foodapp.entity.Kampanya

@Dao
interface KampanyaDao {

    @Query("SELECT * FROM kampanya")
    suspend fun tumKampanyalar() : List<Kampanya>
}