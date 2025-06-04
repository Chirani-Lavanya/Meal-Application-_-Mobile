package com.example.mealapplication

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mealapplication.database.MealRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchForMeals: AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var mealDetailsTextView: TextView

    private lateinit var mealRepository: MealRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_meals)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        searchEditText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        mealDetailsTextView = findViewById(R.id.mealDetailsTextView)

        searchButton.setOnClickListener {
            val searchText = searchEditText.text.toString().trim().lowercase()
            searchEditText.setEnabled(false);

            mealRepository = MealRepository(this)

            GlobalScope.launch {
                // Search meals in the database based on the entered text
                val searchedMeals = mealRepository.searchMeals("%$searchText%")
                Log.d("SearchForMealsActivity", "Searched meals: $searchedMeals")

                runOnUiThread {
                    // Updates the UI
                    displaySearchResults(searchedMeals)
                }
            }
            searchEditText.setEnabled(true);
        }
    }

    private fun displaySearchResults(meals: List<com.example.mealapplication.entities.Meal>) {
        // Show the search results in your UI (e.g., RecyclerView, ListView, TextView, etc.)
        // You can customize this method to display the search results as per your UI design
        // For simplicity, let's assume there is a TextView named 'searchResultTextView' to display the results
        val sb = StringBuilder()
        for (meal in meals) {
            sb.append("Meal: ${meal.meal}\n")
            sb.append("DrinkAlternate: ${meal?.drinkAlternate}\n")
            sb.append("Category: ${meal.category}\n")
            sb.append("Area: ${meal.area}\n")
            sb.append("Instructions: ${meal.instructions}\n")
           sb.append("Meal Thumb: ${meal.mealThumb}\n")
            sb.append("Tags: ${meal.tags}\n")
            sb.append("Youtube: ${meal.youtube}\n")
            if (!meal.ingredient1.isNullOrEmpty()) {
                sb.append("Ingredient1: ${meal.ingredient1}\n")
            }
            if (!meal.ingredient2.isNullOrEmpty()) {
                sb.append("Ingredient2: ${meal.ingredient2}\n")
            }
            if (!meal.ingredient3.isNullOrEmpty()) {
                sb.append("Ingredient3: ${meal.ingredient3}\n")
            }
            if (!meal.ingredient4.isNullOrEmpty()) {
                sb.append("Ingredient4: ${meal.ingredient4}\n")
            }
            if (!meal.ingredient5.isNullOrEmpty()) {
                sb.append("Ingredient5: ${meal.ingredient5}\n")
            }
            if (!meal.ingredient6.isNullOrEmpty()) {
                sb.append("Ingredient6: ${meal.ingredient6}\n")
            }
            if (!meal.ingredient7.isNullOrEmpty()) {
                sb.append("Ingredient7: ${meal.ingredient7}\n")
            }
            if (!meal.ingredient8.isNullOrEmpty()) {
                sb.append("Ingredient8: ${meal.ingredient8}\n")
            }
            if (!meal.ingredient9.isNullOrEmpty()) {
                sb.append("Ingredient9: ${meal.ingredient9}\n")
            }
            if (!meal.ingredient10.isNullOrEmpty()) {
                sb.append("Ingredient10: ${meal.ingredient10}\n")
            }
            if (!meal.ingredient11.isNullOrEmpty()) {
                sb.append("Ingredient11: ${meal.ingredient11}\n")
            }
            if (!meal.ingredient12.isNullOrEmpty()) {
                sb.append("Ingredient12: ${meal.ingredient12}\n")
            }
            if (!meal.ingredient13.isNullOrEmpty()) {
                sb.append("Ingredient13: ${meal.ingredient13}\n")
            }
            if (!meal.ingredient14.isNullOrEmpty()) {
                sb.append("Ingredient14: ${meal.ingredient14}\n")
            }
            if (!meal.ingredient15.isNullOrEmpty()) {
                sb.append("Ingredient15: ${meal.ingredient15}\n")
            }
            if (!meal.ingredient16.isNullOrEmpty()) {
                sb.append("Ingredient16: ${meal.ingredient16}\n")
            }
            if (!meal.ingredient17.isNullOrEmpty()) {
                sb.append("Ingredient17: ${meal.ingredient17}\n")
            }
            if (!meal.ingredient18.isNullOrEmpty()) {
                sb.append("Ingredient18: ${meal.ingredient18}\n")
            }
            if (!meal.ingredient19.isNullOrEmpty()) {
                sb.append("Ingredient19: ${meal.ingredient19}\n")
            }
            if (!meal.ingredient20.isNullOrEmpty()) {
                sb.append("Ingredient20: ${meal.ingredient20}\n")
            }
            if (!meal.measure1.isNullOrEmpty()) {
                sb.append("Measure1: ${meal.measure1}\n")
            }
            if (!meal.measure2.isNullOrEmpty()) {
                sb.append("Measure2: ${meal.measure2}\n")
            }
            if (!meal.measure3.isNullOrEmpty()) {
                sb.append("Measure3: ${meal.measure3}\n")
            }
            if (!meal.measure4.isNullOrEmpty()) {
                sb.append("Measure4: ${meal.measure4}\n")
            }
            if (!meal.measure5.isNullOrEmpty()) {
                sb.append("Measure5: ${meal.measure5}\n")
            }
            if (!meal.measure6.isNullOrEmpty()) {
                sb.append("Measure6: ${meal.measure6}\n")
            }
            if (!meal.measure7.isNullOrEmpty()) {
                sb.append("Measure7: ${meal.measure7}\n")
            }
            if (!meal.measure8.isNullOrEmpty()) {
                sb.append("Measure8: ${meal.measure8}\n")
            }
            if (!meal.measure9.isNullOrEmpty()) {
                sb.append("Measure9: ${meal.measure9}\n")
            }
            if (!meal.measure10.isNullOrEmpty()) {
                sb.append("Measure10: ${meal.measure10}\n")
            }
            if (!meal.measure11.isNullOrEmpty()) {
                sb.append("Measure11: ${meal.measure11}\n")
            }
            if (!meal.measure12.isNullOrEmpty()) {
                sb.append("Measure12: ${meal.measure12}\n")
            }
            if (!meal.measure13.isNullOrEmpty()) {
                sb.append("Measure13: ${meal.measure13}\n")
            }
            if (!meal.measure14.isNullOrEmpty()) {
                sb.append("Measure14: ${meal.measure14}\n")
            }
            if (!meal.measure15.isNullOrEmpty()) {
                sb.append("Measure15: ${meal.measure15}\n")
            }
            if (!meal.measure16.isNullOrEmpty()) {
                sb.append("Measure16: ${meal.measure16}\n")
            }
            if (!meal.measure17.isNullOrEmpty()) {
                sb.append("Measure17: ${meal.measure17}\n")
            }
            if (!meal.measure18.isNullOrEmpty()) {
                sb.append("Measure18: ${meal.measure18}\n")
            }
            if (!meal.measure19.isNullOrEmpty()) {
                sb.append("Measure19: ${meal.measure19}\n")
            }
            if (!meal.measure20.isNullOrEmpty()) {
                sb.append("Measure20: ${meal.measure20}\n")
            }
            sb.append("Source: ${meal.source}\n")
            sb.append("Image Source: ${meal.imageSource}\n")
            sb.append("Creative Commons Confirmed: ${meal.creativeCommonsConfirmed}\n")
            sb.append("Date Modified: ${meal.dateModified}\n")
            sb.append("\n")
        }
        mealDetailsTextView.text = sb.toString()
    }
}