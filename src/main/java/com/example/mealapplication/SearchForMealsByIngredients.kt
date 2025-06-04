package com.example.mealapplication

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mealapplication.database.MealRepository
import com.example.mealapplication.entities.Meal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class SearchForMealsByIngredients : AppCompatActivity() {

    private lateinit var ingredientEditText: EditText
    private lateinit var retrieveButton: Button
    private lateinit var saveButton: Button
    private lateinit var mealDetailsTextView: TextView

    private lateinit var mealRepository: MealRepository
    private lateinit var mealsList: MutableList<Meal>

    private val searchIngredientUrlString = "https://www.themealdb.com/api/json/v1/1/filter.php?i="
    private val mealDetailUrlString = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_meals_by_ingredients)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        mealsList = LinkedList<Meal>();

        ingredientEditText = findViewById(R.id.search_edit_text)
        retrieveButton = findViewById(R.id.retrieve_meals_button)
        saveButton = findViewById(R.id.save_meals_to_db_button)
        mealDetailsTextView = findViewById(R.id.mealDetailsTextView)

        retrieveButton.setOnClickListener {
            val ingredient = ingredientEditText.text.toString()
            ingredientEditText.setEnabled(false);
            retrieveMealsByIngredient(ingredient)
            ingredientEditText.setEnabled(true);
            // Perform the action to retrieve meals based on the entered ingredient
            // You can implement an API call or any other logic here
        }

        saveButton.setOnClickListener {
            mealRepository = MealRepository(this)
            GlobalScope.launch {
                mealsList.forEach { meal ->
                    mealRepository.addMeal(meal)
                }
            }
            if (!mealsList.isNullOrEmpty()) {
                Toast.makeText(this, "Successfully saved to database", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun retrieveDataFromAPI(urlString: String): String? {
        val url = URL(urlString)

        // Create an HttpURLConnection object
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        try {
            // Make the request
            connection.connect()

            // Check the response code
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }

                reader.close()

                // Return the response
                return response.toString()
            } else {
                return null
            }
        } catch (e: Exception) {
            print(e)
            e.printStackTrace()
            // Handle any exceptions that occurred during the request
        } finally {
            // Disconnect and release resources
            connection.disconnect()
        }
        return null
    }

    private fun retrieveMealsByIngredient(ingredient: String) {
        val urlString = "$searchIngredientUrlString$ingredient"
        val responseString = retrieveDataFromAPI(urlString)
        if (responseString != null) {
            extractMealIdFromResponse(responseString)
        }
    }

    private fun extractMealIdFromResponse(responseData: String) {
        val mealDetails = StringBuilder()
        // Extract meal id and get the details
        val obj = JSONObject(responseData)
        val jsonArray = obj.getJSONArray("meals")

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val mealId = jsonObject.getString("idMeal")

            val meal = retrieveMealDetails(mealId)
            mealDetails.append("Meal: ${meal?.meal}\n")
            mealDetails.append("DrinkAlternate: ${meal?.drinkAlternate}\n")
            mealDetails.append("Category: ${meal?.category}\n")
            mealDetails.append("Area: ${meal?.area}\n")
            mealDetails.append("Instructions: ${meal?.instructions}\n")
            mealDetails.append("Thumbnail: ${meal?.mealThumb}\n")
            mealDetails.append("Tags: ${meal?.tags}\n")
            mealDetails.append("Youtube: ${meal?.youtube}\n")
            if (!meal?.ingredient1.isNullOrEmpty()) {
                mealDetails.append("Ingredient1: $meal?.ingredient1\n")
            }
            if (!meal?.ingredient2.isNullOrEmpty()) {
                mealDetails.append("Ingredient2: ${meal?.ingredient2}\n")
            }
            if (!meal?.ingredient3.isNullOrEmpty()) {
                mealDetails.append("Ingredient3: ${meal?.ingredient3}\n")
            }
            if (!meal?.ingredient4.isNullOrEmpty()) {
                mealDetails.append("Ingredient4: ${meal?.ingredient4}\n")
            }
            if (!meal?.ingredient5.isNullOrEmpty()) {
                mealDetails.append("Ingredient5: ${meal?.ingredient5}\n")
            }
            if (!meal?.ingredient6.isNullOrEmpty()) {
                mealDetails.append("Ingredient6: ${meal?.ingredient6}\n")
            }
            if (!meal?.ingredient7.isNullOrEmpty()) {
                mealDetails.append("Ingredient7: ${meal?.ingredient7}\n")
            }
            if (!meal?.ingredient8.isNullOrEmpty()) {
                mealDetails.append("Ingredient8: ${meal?.ingredient8}\n")
            }
            if (!meal?.ingredient9.isNullOrEmpty()) {
                mealDetails.append("Ingredient9: ${meal?.ingredient9}\n")
            }
            if (!meal?.ingredient10.isNullOrEmpty()) {
                mealDetails.append("Ingredient10: ${meal?.ingredient10}\n")
            }
            if (!meal?.ingredient11.isNullOrEmpty()) {
                mealDetails.append("Ingredient11: ${meal?.ingredient11}\n")
            }
            if (!meal?.ingredient12.isNullOrEmpty()) {
                mealDetails.append("Ingredient12: ${meal?.ingredient12}\n")
            }
            if (!meal?.ingredient13.isNullOrEmpty()) {
                mealDetails.append("Ingredient13: ${meal?.ingredient13}\n")
            }
            if (!meal?.ingredient14.isNullOrEmpty()) {
                mealDetails.append("Ingredient14: ${meal?.ingredient14}\n")
            }
            if (!meal?.ingredient15.isNullOrEmpty()) {
                mealDetails.append("Ingredient15: ${meal?.ingredient15}\n")
            }
            if (!meal?.ingredient16.isNullOrEmpty()) {
                mealDetails.append("Ingredient16: ${meal?.ingredient16}\n")
            }
            if (!meal?.ingredient17.isNullOrEmpty()) {
                mealDetails.append("Ingredient17: ${meal?.ingredient17}\n")
            }
            if (!meal?.ingredient18.isNullOrEmpty()) {
                mealDetails.append("Ingredient18: ${meal?.ingredient18}\n")
            }
            if (!meal?.ingredient19.isNullOrEmpty()) {
                mealDetails.append("Ingredient19: ${meal?.ingredient19}\n")
            }
            if (!meal?.ingredient20.isNullOrEmpty()) {
                mealDetails.append("Ingredient20: ${meal?.ingredient20}\n")
            }
            if (!meal?.measure1.isNullOrEmpty()) {
                mealDetails.append("Measure1: ${meal?.measure1}\n")
            }
            if (!meal?.measure2.isNullOrEmpty()) {
                mealDetails.append("Measure2: ${meal?.measure2}\n")
            }
            if (!meal?.measure3.isNullOrEmpty()) {
                mealDetails.append("Measure3: ${meal?.measure3}\n")
            }
            if (!meal?.measure4.isNullOrEmpty()) {
                mealDetails.append("Measure4: ${meal?.measure4}\n")
            }
            if (!meal?.measure5.isNullOrEmpty()) {
                mealDetails.append("Measure5: ${meal?.measure5}\n")
            }
            if (!meal?.measure6.isNullOrEmpty()) {
                mealDetails.append("Measure6: ${meal?.measure6}\n")
            }
            if (!meal?.measure7.isNullOrEmpty()) {
                mealDetails.append("Measure7: ${meal?.measure7}\n")
            }
            if (!meal?.measure8.isNullOrEmpty()) {
                mealDetails.append("Measure8: ${meal?.measure8}\n")
            }
            if (!meal?.measure9.isNullOrEmpty()) {
                mealDetails.append("Measure9: ${meal?.measure9}\n")
            }
            if (!meal?.measure10.isNullOrEmpty()) {
                mealDetails.append("Measure10: ${meal?.measure10}\n")
            }
            if (!meal?.measure11.isNullOrEmpty()) {
                mealDetails.append("Measure11: ${meal?.measure11}\n")
            }
            if (!meal?.measure12.isNullOrEmpty()) {
                mealDetails.append("Measure12: ${meal?.measure12}\n")
            }
            if (!meal?.measure13.isNullOrEmpty()) {
                mealDetails.append("Measure13: ${meal?.measure13}\n")
            }
            if (!meal?.measure14.isNullOrEmpty()) {
                mealDetails.append("Measure14: ${meal?.measure14}\n")
            }
            if (!meal?.measure15.isNullOrEmpty()) {
                mealDetails.append("Measure15: ${meal?.measure15}\n")
            }
            if (!meal?.measure16.isNullOrEmpty()) {
                mealDetails.append("Measure16: ${meal?.measure16}\n")
            }
            if (!meal?.measure17.isNullOrEmpty()) {
                mealDetails.append("Measure17: ${meal?.measure17}\n")
            }
            if (!meal?.measure18.isNullOrEmpty()) {
                mealDetails.append("Measure18: ${meal?.measure18}\n")
            }
            if (!meal?.measure19.isNullOrEmpty()) {
                mealDetails.append("Measure19: ${meal?.measure19}\n")
            }
            if (!meal?.measure20.isNullOrEmpty()) {
                mealDetails.append("Measure20: ${meal?.measure20}\n")
            }

            mealDetails.append("\n")
        }
        mealDetailsTextView.text = mealDetails
    }

    private fun parseMealsFromResponse(responseData: String): Meal {
        // Create a Meal object and return it
        val obj = JSONObject(responseData)
        val jsonArray = obj.getJSONArray("meals")
        val jsonObject = jsonArray.getJSONObject(0)
        val meal = Meal(
            mealId = jsonObject.getString("idMeal"),
            meal = jsonObject.getString("strMeal"),
            drinkAlternate = jsonObject.getString("strDrinkAlternate"),
            category = jsonObject.getString("strCategory"),
            area = jsonObject.getString("strArea"),
            instructions = jsonObject.getString("strInstructions"),
            mealThumb = jsonObject.getString("strMealThumb"),
            tags = jsonObject.getString("strTags"),
            youtube = jsonObject.getString("strYoutube"),
            ingredient1 = jsonObject.getString("strIngredient1"),
            ingredient2 = jsonObject.getString("strIngredient2"),
            ingredient3 = jsonObject.getString("strIngredient3"),
            ingredient4 = jsonObject.getString("strIngredient4"),
            ingredient5 = jsonObject.getString("strIngredient5"),
            ingredient6 = jsonObject.getString("strIngredient6"),
            ingredient7 = jsonObject.getString("strIngredient7"),
            ingredient8 = jsonObject.getString("strIngredient8"),
            ingredient9 = jsonObject.getString("strIngredient9"),
            ingredient10 = jsonObject.getString("strIngredient10"),
            ingredient11 = jsonObject.getString("strIngredient11"),
            ingredient12 = jsonObject.getString("strIngredient12"),
            ingredient13 = jsonObject.getString("strIngredient13"),
            ingredient14 = jsonObject.getString("strIngredient14"),
            ingredient15 = jsonObject.getString("strIngredient15"),
            ingredient16 = jsonObject.getString("strIngredient16"),
            ingredient17 = jsonObject.getString("strIngredient17"),
            ingredient18 = jsonObject.getString("strIngredient18"),
            ingredient19 = jsonObject.getString("strIngredient19"),
            ingredient20 = jsonObject.getString("strIngredient20"),
            measure1 = jsonObject.getString("strMeasure1"),
            measure2 = jsonObject.getString("strMeasure2"),
            measure3 = jsonObject.getString("strMeasure3"),
            measure4 = jsonObject.getString("strMeasure4"),
            measure5 = jsonObject.getString("strMeasure5"),
            measure6 = jsonObject.getString("strMeasure6"),
            measure7 = jsonObject.getString("strMeasure7"),
            measure8 = jsonObject.getString("strMeasure8"),
            measure9 = jsonObject.getString("strMeasure9"),
            measure10 = jsonObject.getString("strMeasure10"),
            measure11 = jsonObject.getString("strMeasure11"),
            measure12 = jsonObject.getString("strMeasure12"),
            measure13 = jsonObject.getString("strMeasure13"),
            measure14 = jsonObject.getString("strMeasure14"),
            measure15 = jsonObject.getString("strMeasure15"),
            measure16 = jsonObject.getString("strMeasure16"),
            measure17 = jsonObject.getString("strMeasure17"),
            measure18 = jsonObject.getString("strMeasure18"),
            measure19 = jsonObject.getString("strMeasure19"),
            measure20 = jsonObject.getString("strMeasure20"),
            source = jsonObject.getString("strSource"),
            imageSource = jsonObject.getString("strImageSource"),
            creativeCommonsConfirmed = jsonObject.getString("strCreativeCommonsConfirmed"),
            dateModified = jsonObject.getString("dateModified")
        )
        mealsList.add(meal)
        return meal
    }

    private fun retrieveMealDetails(id: String): Meal? {
        val urlString = "$mealDetailUrlString$id"
        val responseString = retrieveDataFromAPI(urlString)
        if (responseString != null) {
            val meal = parseMealsFromResponse(responseString)
            return meal
        }
        return null
    }
}