package com.fatihbaser.kotlinmeals.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatihbaser.kotlinmeals.Model.Meal

class MealViewModel:ViewModel() {

    val mealLiveData=MutableLiveData<Meal>()

    fun getDataFromRoom(){
        val meal=Meal("kofte","et,tuz,seker","firinda pisir","www.ss.com")
        mealLiveData.value=meal
    }
}