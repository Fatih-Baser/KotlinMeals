package com.fatihbaser.kotlinmeals.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FavoriteMeal(
    @ColumnInfo(name="name")
    val mealName : String?,
    @ColumnInfo(name="resim")
    val resim  : String?){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
