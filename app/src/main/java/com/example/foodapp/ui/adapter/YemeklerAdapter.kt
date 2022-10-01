package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.YemeklerCardTasarimBinding
import com.example.foodapp.entity.Yemekler
import com.example.foodapp.picasso.Picasso
import com.example.foodapp.ui.fragment.AnasayfaFragmentDirections
import com.example.foodapp.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.foodapp.ui.viewmodel.YemekDetayFragmentViewModel


class YemeklerAdapter(var mContext : Context,
                      var yemeklerListesi : List<Yemekler>,
                      var viewModel : AnasayfaFragmentViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.YemekCardTasarimTutucu>(){
    lateinit var viewModel2 : YemekDetayFragmentViewModel

    inner class YemekCardTasarimTutucu(tasarim : YemeklerCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim : YemeklerCardTasarimBinding
        init {
            this.tasarim = tasarim
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekCardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:YemeklerCardTasarimBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.yemekler_card_tasarim,parent,false)
        return YemekCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: YemekCardTasarimTutucu, position: Int) {
        val yemekler = yemeklerListesi.get(position)
        val y = holder.tasarim
        y.yemekNesnesi = yemekler

        Picasso().resmiGoster(y.yemekNesnesi!!.yemek_resim_adi,y.imageViewYemekResim)

        y.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemekler)
            Navigation.findNavController(it).navigate(gecis)
        }


    }
    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }






}