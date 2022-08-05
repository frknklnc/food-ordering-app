package com.example.foodapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.Kampanya
import com.example.foodapp.room.KampanyaDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KampanyaRepository (var kdao:KampanyaDao) {
    var kampanyaListesi:MutableLiveData<List<Kampanya>>

    init {
        kampanyaListesi = MutableLiveData()
    }

    fun kampanyaGetir(): MutableLiveData<List<Kampanya>>{
        return kampanyaListesi
    }

    fun kampanyaAl(){
        val kampanya = CoroutineScope(Dispatchers.Main).launch {
            kampanyaListesi.value = kdao.tumKampanyalar()
        }

    }
}