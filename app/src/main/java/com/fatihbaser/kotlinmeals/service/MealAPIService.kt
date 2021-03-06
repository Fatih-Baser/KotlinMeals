package com.fatihbaser.kotlinmeals.service

import com.fatihbaser.kotlinmeals.Model.Meal
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MealAPIService {
    //https://raw.githubusercontent.com/Fatih-Baser/mealsjson/master/db.json

    private val BASE_URL="https://raw.githubusercontent.com/"



    private val api= Retrofit.Builder().baseUrl(BASE_URL ).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MealAPI::class.java)


    fun getData (): Single<List<Meal>> {
        return api.getMeals()
    }

}