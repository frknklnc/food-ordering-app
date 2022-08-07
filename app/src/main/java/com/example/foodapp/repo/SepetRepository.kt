package com.example.foodapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.*
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.SepetDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SepetRepository(var sdao:SepetDao) {
    var sepetYemekListesi: MutableLiveData<List<Sepet>>

    init {
        sepetYemekListesi = MutableLiveData()
    }


    fun sepettekiYemekleriGetir(): MutableLiveData<List<Sepet>> {
        return sepetYemekListesi
    }

    fun tumSepettekiYemekleriAl(kullanici_adi:String) {
        var hashMap = HashMap<String,Sepet>()
        val other: List<Sepet> = emptyList()
        sdao.sepettekiYemekler(kullanici_adi).enqueue(object : Callback<SepetCevap> {
            override fun onResponse(call: Call<SepetCevap>?, response: Response<SepetCevap>) {
                try {
                    val liste = response.body().sepet_yemekler
                    for (i in liste){
                        if (hashMap.containsKey(i.yemek_adi)){
                            hashMap.get(i.yemek_adi)!!.yemek_siparis_adet += i.yemek_siparis_adet

                        }else{
                            hashMap.put(i.yemek_adi,i)
                        }

                    }
                    sepetYemekListesi.value = hashMap.values.toList()
                    Log.e("tumsepet", "${hashMap.values.toList()}")
                }catch (e:Exception){
                    Log.e("tumsepet",e.stackTrace.toString())
                }
            }

            override fun onFailure(call: Call<SepetCevap>?, t: Throwable?) {}
        })

    }


    fun sepeteEkle(yemek_adi : String, yemek_resim_adi : String,
                        yemek_fiyat : Int, yemek_siparis_adet : Int, kullanici_adi : String) {
        sdao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(object : Callback<CRUDCevap> {

            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })

    }

    fun sepettenSil(sepet_yemek_id:Int,kullanici_adi: String){
        sdao.sepettenYemekSil(sepet_yemek_id,kullanici_adi).enqueue(object :Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message
                try {
                    tumSepettekiYemekleriAl(kullanici_adi)
                }catch (e:Exception){
                    Log.e("sepetsil",e.stackTrace.toString())
                }

            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })

    }

}