package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.Kampanya
import com.example.foodapp.repo.KampanyaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KampanyaFragmentViewModel @Inject constructor(var krepo : KampanyaRepository) : ViewModel() {
    var kampanyaListesi = MutableLiveData<List<Kampanya>>()

    init {
        kampanyaYukle()
        kampanyaListesi = krepo.kampanyaGetir()

    }

    fun kampanyaYukle(){
        krepo.kampanyaAl()
    }

}