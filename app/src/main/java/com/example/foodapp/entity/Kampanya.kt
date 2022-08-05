package com.example.foodapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "kampanya")
data class Kampanya(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "kampanya_id") @NotNull var kampanya_id:Int,
                    @ColumnInfo(name = "kampanya_adi") @NotNull var kampanya_adi:String) :Serializable {
}