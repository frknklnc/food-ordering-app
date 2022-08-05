package com.example.foodapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodapp.entity.Kampanya

@Database(entities = [Kampanya::class], version = 1)
abstract class Veritabani : RoomDatabase(){
    abstract fun getKampanyaDao() : KampanyaDao
}