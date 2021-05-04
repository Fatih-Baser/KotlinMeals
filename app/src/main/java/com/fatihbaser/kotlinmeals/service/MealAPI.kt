package com.fatihbaser.kotlinmeals.service

import com.fatihbaser.kotlinmeals.Model.Meal
import io.reactivex.Single
import retrofit2.http.GET

interface MealAPI {
    //get
    //post
    //https://raw.githubusercontent.com/Fatih-Baser/mealsjson/master/db.json
    @GET("Fatih-Baser/mealsjson/master/db.json")
    fun getMeals(): Single<List<Meal>>


}