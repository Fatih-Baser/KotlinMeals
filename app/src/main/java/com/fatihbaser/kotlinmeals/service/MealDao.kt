package com.fatihbaser.kotlinmeals.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fatihbaser.kotlinmeals.Model.FavoriteMeal
import com.fatihbaser.kotlinmeals.Model.Meal

@Dao
interface MealDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertfavorite(movie: FavoriteMeal?)

    @Delete
    fun deletefavorite(movie: FavoriteMeal?)

    @Query("SELECT * FROM favoritemeal")
    fun getAllfavorite(): LiveData<List<FavoriteMeal>>

    @Query("SELECT * FROM favoritemeal WHERE uuid = :favoriteId")
    fun getSinglefavorite(favoriteId:Int): LiveData<FavoriteMeal>

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