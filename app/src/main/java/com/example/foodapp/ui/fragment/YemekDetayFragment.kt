package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentYemekDetayBinding
import com.example.foodapp.picasso.Picasso
import com.example.foodapp.repo.AnimasyonRepository
import com.example.foodapp.ui.viewmodel.YemekDetayFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_yemek_detay.*

@AndroidEntryPoint

class YemekDetayFragment : Fragment() {
    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay, container, false)
        tasarim.yemekDetayFragment = this

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarYemekDetay)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        val bundle:YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        tasarim.yemekNesnesi = gelenYemek

        tasarim.buttonSepeteEkle.setOnClickListener {
            sepeteEkle(gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,Integer.valueOf(textViewYemekAdet.getText().toString()),"user")
        }

        Picasso().resmiGoster(gelenYemek.yemek_resim_adi,tasarim.imageViewYemekDetay)

        var adet = 1
        tasarim.imageViewArti.setOnClickListener {
            adet += 1
            tasarim.textViewYemekAdet.text = adet.toString()
        }

        tasarim.imageViewEksi.setOnClickListener {
            adet -= 1

            if(adet<0) {adet = 0}
            tasarim.textViewYemekAdet.text = adet.toString()
        }

        return tasarim.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun sepeteEkle(yemek_adi : String, yemek_resim_adi : String, yemek_fiyat : Int, yemek_siparis_adet : Int, kullanici_adi : String){
        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        Navigation.findNavController(tasarim.buttonSepeteEkle).navigate(R.id.detaydanAnasayfaGecis)
        /*Snackbar.make(requireView(),"Ürün sepete eklendi.",Snackbar.LENGTH_SHORT).show()*/
        AnimasyonRepository.animasyon(requireContext(),R.layout.activity_sepete_eklendi)
    }
    fun anasayfaGecis(){
        Navigation.findNavController(tasarim.imageViewGeri).navigate(R.id.detaydanAnasayfaGecis)
    }



}