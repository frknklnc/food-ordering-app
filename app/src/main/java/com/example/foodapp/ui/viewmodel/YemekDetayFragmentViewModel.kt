package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodapp.repo.SepetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YemekDetayFragmentViewModel @Inject constructor(var srepo:SepetRepository): ViewModel() {
    fun sepeteEkle(yemek_adi : String, yemek_resim_adi : String, yemek_fiyat : Int, yemek_siparis_adet : Int, kullanici_adi : String){
        srepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }
}