package com.example.foodapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.Yemekler
import com.example.foodapp.repo.SepetRepository
import com.example.foodapp.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel(){

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()
    }

    fun ara(aramaKelimesi: String) {
        if (aramaKelimesi.length == 0){
            yrepo.tumYemekleriAl()
        }
        yemeklerListesi.value = yemeklerListesi.value!!.filter { yemekler -> yemekler.yemek_adi.lowercase().contains(aramaKelimesi.lowercase()) }

    }

    fun artanFiyat(){
        yrepo.artanFiyat()
    }

    fun azalanFiyat(){
        yrepo.azalanFiyat()
    }

}