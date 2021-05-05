package com.fatihbaser.kotlinmeals.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Meal(
        @ColumnInfo(name="name")
        @SerializedName("name")
        val mealName : String?,
        @ColumnInfo(name="malzemeler")
        @SerializedName("malzemeler")
        val malzemelerName: String?,
        @ColumnInfo(name="tarifi")
        @SerializedName("tarifi")
        val tarifi : String?,
        @ColumnInfo(name="resim")
        @SerializedName("resim")
        val resim  : String?){
        @PrimaryKey(autoGenerate = true)
        var uuid:Int=0
}

