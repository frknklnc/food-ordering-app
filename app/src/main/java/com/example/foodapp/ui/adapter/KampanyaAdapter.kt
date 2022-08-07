package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.KampanyaCardTasarimBinding
import com.example.foodapp.entity.Kampanya
import com.example.foodapp.ui.viewmodel.KampanyaFragmentViewModel
import com.google.android.material.snackbar.Snackbar

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

        k.imageViewKampanyaKullan.setOnClickListener {
            Snackbar.make(it,"Kampanya uygulandı",Snackbar.LENGTH_SHORT).show()
            Navigation.findNavController(k.imageViewKampanyaKullan).navigate(R.id.kampanyadanSepetGecis)
        }

    }

    override fun getItemCount(): Int {
        return kampanyaListesi.size
    }
}