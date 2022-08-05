package com.example.foodapp.picasso

import android.widget.ImageView
import com.squareup.picasso.Picasso


class Picasso {

    fun resmiGoster(yemek_resim_adi:String, image: ImageView){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$yemek_resim_adi"

        Picasso.get().load(url).into(image)

    }
}