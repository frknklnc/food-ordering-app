package com.example.foodapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentSepetBinding
import com.example.foodapp.entity.Sepet
import com.example.foodapp.repo.AnimasyonRepository
import com.example.foodapp.ui.adapter.SepetAdapter
import com.example.foodapp.ui.viewmodel.SepetFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SepetFragment : Fragment() {

    private lateinit var tasarim:FragmentSepetBinding
    private lateinit var viewModel : SepetFragmentViewModel
    private lateinit var tumSepet : List<Sepet>
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet,container, false)
        tasarim.sepetFragment = this

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarSepet)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.sepetYemekleriYukle()

        viewModel.sepetYemekListesi.observe(viewLifecycleOwner){
            val adapter = SepetAdapter(requireContext(),it,viewModel)
            tasarim.sepetAdapter = adapter
            tumSepet = viewModel.sepetYemekListesi.value!!

            tasarim.sepetToplamFiyat.text = sepetToplamFiyat(tumSepet,tumSepet.size).toString() + " ₺"


        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYemekleriYukle()
    }


    fun sepetToplamFiyat(tumSepet: List<Sepet>, sepetsize:Int) : Int {
        var toplamFiyat = 0

        for (i in 0..sepetsize-1) {
            toplamFiyat += (tumSepet.get(i).yemek_siparis_adet.toInt() * tumSepet.get(i).yemek_fiyat.toInt())
        }

        return toplamFiyat
    }

    fun sepetiOnayla(){
        viewModel.sepetiBosalt()
        AnimasyonRepository.animasyon(requireContext(),R.layout.activity_siparis_animasyon)
        Navigation.findNavController(tasarim.imageViewKampanya).navigate(R.id.sepettenAnasayfaGecis)
    }

    fun gecis(){
        Navigation.findNavController(tasarim.imageViewKampanya).navigate(R.id.sepetKampanyaGecis)
    }


}