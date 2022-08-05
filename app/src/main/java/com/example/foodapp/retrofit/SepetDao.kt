package com.example.foodapp.retrofit

import com.example.foodapp.entity.CRUDCevap
import com.example.foodapp.entity.SepetCevap
import com.google.gson.annotations.Expose
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.io.Serializable

interface SepetDao {

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepettekiYemekler(@Field("kullanici_adi") kullanici_adi: String) : Call<SepetCevap>



    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteEkle(@Field("yemek_adi") yemek_adi:String,
                   @Field("yemek_resim_adi") yemek_resim_adi:String,
                   @Field("yemek_fiyat") yemek_fiyat:Int,
                   @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                   @Field("kullanici_adi") kullanici_adi:String) : Call<CRUDCevap>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenYemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                         @Field("kullanici_adi") kullanici_adi: String) :Call<CRUDCevap>


}