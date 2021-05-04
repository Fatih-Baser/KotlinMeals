package com.fatihbaser.kotlinmeals.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatihbaser.kotlinmeals.Model.Meal
import com.fatihbaser.kotlinmeals.service.MealDatabase
import kotlinx.coroutines.launch

class MealViewModel(application: Application) :BaseViewModel(application) {

    val mealLiveData=MutableLiveData<Meal>()

    fun getDataFromRoom(uuid:Int){

        launch {
            val dao=MealDatabase(getApplication()).mealDao()
            val meal=dao.getCountry(uuid )
            mealLiveData.value=meal
        }
    }
}