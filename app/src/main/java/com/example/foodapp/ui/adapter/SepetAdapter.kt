package com.example.foodapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.SepetCardTasarimBinding
import com.example.foodapp.databinding.YemeklerCardTasarimBinding
import com.example.foodapp.entity.Sepet
import com.example.foodapp.picasso.Picasso
import com.example.foodapp.ui.fragment.AnasayfaFragmentDirections
import com.example.foodapp.ui.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(var mContext : Context,
                   var sepetYemeklerListesi : List<Sepet>,
                   var viewModel : SepetFragmentViewModel)
    : RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>(){
    inner class SepetCardTasarimTutucu(tasarim : SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {

        var tasarim : SepetCardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: SepetCardTasarimBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.sepet_card_tasarim,parent,false)
        return SepetCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepet = sepetYemeklerListesi.get(position)
        val s = holder.tasarim
        s.sepetYemekNesnesi = sepet

        Picasso().resmiGoster(s.sepetYemekNesnesi!!.yemek_resim_adi,s.imageViewSepetResim)

        s.imageViewSepetYemekSil.setOnClickListener {
            Snackbar.make(it,"${sepet.yemek_adi} silinsin mi?",Snackbar.LENGTH_LONG).
            setAction("EVET"){
                viewModel.sepettenSil(sepet.sepet_yemek_id,"user")
            }.setActionTextColor(Color.RED)
                .setTextColor(Color.BLACK)
                .setBackgroundTint(Color.WHITE)
                .show()

        }

    }
    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }

}