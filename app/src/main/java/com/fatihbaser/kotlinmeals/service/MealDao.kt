package com.fatihbaser.kotlinmeals.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fatihbaser.kotlinmeals.model.Meal

@Dao
interface MealDao {

    //Data Access Object
    @Insert
    suspend fun  insertAll(vararg countries: Meal) : List<Long>

    //Insert -> insert into
    // suspend -> coroutine , pauese ^ resume
    //vararg -> multiple country objects
    //List<Long> -> pprimary keys
    @Query("SELECT * FROM meal")
    suspend fun getAllCountries() : List<Meal>

    @Query("SELECT * FROM meal WHERE uuid = :mealId")
    suspend fun getCountry(mealId : Int) : Meal

    @Query("DELETE FROM meal")
    suspend fun deleteAllCountries()
}