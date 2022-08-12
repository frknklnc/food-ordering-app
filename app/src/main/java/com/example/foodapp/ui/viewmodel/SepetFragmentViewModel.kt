package com.example.foodapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.Sepet
import com.example.foodapp.entity.Yemekler
import com.example.foodapp.repo.SepetRepository
import com.example.foodapp.retrofit.SepetDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetFragmentViewModel @Inject constructor(var srepo:SepetRepository) : ViewModel () {
    var sepetYemekListesi = MutableLiveData<List<Sepet>>()
    var kullanici_adi = "user"

    init {
        sepetYemekleriYukle()
        sepetYemekListesi = srepo.sepettekiYemekleriGetir()

    }

    fun yemekleriGetir(){
        srepo.sepettekiYemekleriGetir()
    }

    fun sepetYemekleriYukle(){
        srepo.tumSepettekiYemekleriAl(kullanici_adi)

    }

    fun sepettenSil(sepet_yemek_id:Int,kullanici_adi:String){
        srepo.sepettenSil(sepet_yemek_id,kullanici_adi)
        if (sepetYemekListesi.value!!.size -1 == 0){
            sepetYemekListesi.value = emptyList()
        }

    }

    fun sepetiBosalt(){
        val tumYemek = sepetYemekListesi.value!!
        for (i in tumYemek){
            sepettenSil(i.sepet_yemek_id,kullanici_adi)
            if (tumYemek.indexOf(i) == tumYemek.size-1){
                sepetYemekListesi.value = emptyList()
                }
            /*if (sepetYemekListesi.value!!.size -1 == 0){
                sepetYemekListesi.value = emptyList()
            }*/
        }
        srepo.tumSepettekiYemekleriAl(kullanici_adi)

    }



}