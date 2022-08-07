package com.example.foodapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.Yemekler
import com.example.foodapp.entity.YemeklerCevap
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerRepository(var ydao: YemeklerDao) {

    var yemekListesi: MutableLiveData<List<Yemekler>>

    init {
        ydao = ApiUtils.getYemeklerDao()
        yemekListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>>{
        return yemekListesi
    }

    fun tumYemekleriAl(){
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemekListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })

    }

    fun artanFiyat(){
        ydao.tumYemekler().enqueue(object :Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>){
                val menu = response.body().yemekler

                val artanFiyat = menu.sortedWith(compareBy { it.yemek_fiyat })
                yemekListesi.value = artanFiyat
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun azalanFiyat(){
        ydao.tumYemekler().enqueue(object :Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>){
                val menu = response.body().yemekler

                val azalanFiyat = menu.sortedWith(compareBy { it.yemek_fiyat }).reversed()
                yemekListesi.value = azalanFiyat
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }


}