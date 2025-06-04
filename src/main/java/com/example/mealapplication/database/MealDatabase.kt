package com.example.mealapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mealapplication.dao.MealDao
import com.example.mealapplication.entities.Meal

@Database(entities = [Meal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var instance: MealDatabase? = null
        private const val DATABASE_NAME = "meal_database.db"

        fun getInstance(context: Context): MealDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MealDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MealDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}