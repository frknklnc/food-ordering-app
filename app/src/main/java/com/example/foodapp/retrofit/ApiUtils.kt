package com.example.foodapp.retrofit

import java.util.*

class ApiUtils {
    companion object{

        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDao(): YemeklerDao{
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
        }

        fun getSepetDao(): SepetDao{
            return RetrofitClient.getClient(BASE_URL).create(SepetDao::class.java)
        }
    }
}