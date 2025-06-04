package com.example.mealapplication.database

import android.content.Context
import com.example.mealapplication.entities.Meal

class MealRepository(context: Context) {
    private val mealDao = MealDatabase.getInstance(context).mealDao()

    suspend fun addMeal(meal: Meal) {
        mealDao.insert(meal)
    }

    suspend fun getAllMeals(): List<Meal> {
        return mealDao.getAllMeals()
    }

    suspend fun addMeals(meals: List<Meal>) {
        mealDao.insertAll(meals)
    }

    suspend fun searchMeals(searchText: String): List<Meal> {
        return mealDao.searchMeals(searchText)
    }
}