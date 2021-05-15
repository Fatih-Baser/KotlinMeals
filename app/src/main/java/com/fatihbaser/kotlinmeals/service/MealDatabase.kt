package com.fatihbaser.kotlinmeals.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fatihbaser.kotlinmeals.Model.FavoriteMeal
import com.fatihbaser.kotlinmeals.Model.Meal


@Database(entities = [Meal::class,FavoriteMeal::class],version = 2 )
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao():MealDao

    companion object{//heryerde ulasabilmek icin

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate( database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `favoritemeal` (`name` TEXT , uuid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,`resim` TEXT )")
            }
        }

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
            .addMigrations(MIGRATION_1_2)
                .build()
    }
}