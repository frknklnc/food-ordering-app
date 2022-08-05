package com.example.foodapp.entity

import com.google.gson.annotations.SerializedName

data class CRUDCevap(@SerializedName("success")var success:Int,
                     @SerializedName("message")var message:String) { // create,uptade,delete class
}