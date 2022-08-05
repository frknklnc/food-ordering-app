package com.example.foodapp.retrofit

import com.example.foodapp.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.GET

interface YemeklerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemeklerCevap>


}