package com.fatihbaser.kotlinmeals.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatihbaser.kotlinmeals.Model.Meal

//observable =gozlemlenebilir
//observer =gozlemci
//Mutable =degistirilebilir
class FeedViewModel :ViewModel(){

    val meals= MutableLiveData<List<Meal>>()
    val mealError=MutableLiveData<Boolean>()
    val mealLoading=MutableLiveData<Boolean>()

    fun refleshData(){
        val meal=Meal("kofte","et,tuz,seker","firinda pisir","www.ss.com")
        val meal2=Meal("menemen","sogan,domates,seker","tavada pisir","www.ss.com")
        val meal3=Meal("pogaca","hamur,tuz,yag","firinda 40dk  pisir","www.ss.com")
        val meallist= arrayListOf<Meal>(meal,meal2,meal3)
        meals.value=meallist
        mealError.value=false
        mealLoading.value=false
    }
}