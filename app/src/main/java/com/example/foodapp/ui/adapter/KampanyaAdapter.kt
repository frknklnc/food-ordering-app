package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.KampanyaCardTasarimBinding
import com.example.foodapp.entity.Kampanya
import com.example.foodapp.ui.viewmodel.KampanyaFragmentViewModel

class KampanyaAdapter(var mContext: Context,var kampanyaListesi:List<Kampanya>,
                      var viewModel: KampanyaFragmentViewModel)
    :RecyclerView.Adapter<KampanyaAdapter.KampanyaTasarimTutucu>() {
    inner class KampanyaTasarimTutucu(tasarim:KampanyaCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim : KampanyaCardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KampanyaTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:KampanyaCardTasarimBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.kampanya_card_tasarim,parent,false)
        return KampanyaTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: KampanyaTasarimTutucu, position: Int) {
        val kampanya = kampanyaListesi.get(position)
        val k = holder.tasarim
        k.kampanyaNesnesi = kampanya

    }

    override fun getItemCount(): Int {
        return kampanyaListesi.size
    }
}