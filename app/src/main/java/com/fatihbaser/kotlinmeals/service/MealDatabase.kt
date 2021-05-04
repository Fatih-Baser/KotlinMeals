package com.fatihbaser.kotlinmeals.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fatihbaser.kotlinmeals.Model.Meal

@Database(entities = arrayOf(Meal::class),version = 1 )
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao():MealDao

    companion object{//heryerde ulasabilmek icin

      //  @volatile = herhangi bir degiskeni tanimladigimizda farkli treadlerde gorunur olur
      @Volatile private var instance : MealDatabase? =null

        private val lock =Any()

        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance=it
            }
        }
        private fun makeDatabase(context: Context)=Room.databaseBuilder(
                context.applicationContext,MealDatabase::class.java,"mealdatabase")
                .build()
    }
}