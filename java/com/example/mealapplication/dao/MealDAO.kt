package com.example.mealapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mealapplication.entities.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals: List<Meal>)

    @Query("SELECT * FROM meals")
    fun getAllMeals(): List<Meal>

    @Query("SELECT * FROM meals WHERE lower(meal) LIKE :searchText OR lower(ingredient1) LIKE :searchText OR lower(ingredient2) LIKE :searchText OR lower(ingredient3) LIKE :searchText OR lower(ingredient4) LIKE :searchText OR lower(ingredient5) LIKE :searchText OR lower(ingredient6) LIKE :searchText OR lower(ingredient7) LIKE :searchText OR lower(ingredient8) LIKE :searchText OR lower(ingredient9) LIKE :searchText OR lower(ingredient10) LIKE :searchText OR lower(ingredient11) LIKE :searchText OR lower(ingredient12) LIKE :searchText OR lower(ingredient13) LIKE :searchText OR lower(ingredient14) LIKE :searchText OR lower(ingredient15) LIKE :searchText OR lower(ingredient16) LIKE :searchText OR lower(ingredient17) LIKE :searchText OR lower(ingredient18) LIKE :searchText OR lower(ingredient19) LIKE :searchText OR lower(ingredient20) LIKE :searchText")
    fun searchMeals(searchText: String): List<Meal>
}