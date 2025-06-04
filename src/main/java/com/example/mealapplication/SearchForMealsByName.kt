package com.example.mealapplication

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mealapplication.database.MealRepository
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchForMealsByName: AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var mealDetailsTextView: TextView

    private lateinit var mealRepository: MealRepository

    private val searchMealUrlString = "https://www.themealdb.com/api/json/v1/1/search.php?s="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_meals_by_name)

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
            retrieveMealsByName(searchText)
            searchEditText.setEnabled(true);
        }
    }

    private fun retrieveMealsByName(searchText: String) {
        val urlString = "$searchMealUrlString$searchText"
        val responseString = retrieveDataFromAPI(urlString)
        if (responseString != null) {
            extractMealsFromResponse(responseString)
        }
    }

    private fun extractMealsFromResponse(responseData: String) {
        val mealDetails = StringBuilder()
        // Extract meal id and get the details
        val obj = JSONObject(responseData)
        val jsonArray = obj.getJSONArray("meals")

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            mealDetails.append("Meal ID: ${jsonObject.getString("idMeal")}\n")
            mealDetails.append("Meal: ${jsonObject.getString("strMeal")}\n")
            mealDetails.append("DrinkAlternate: ${jsonObject.getString("strDrinkAlternate")}\n")
            mealDetails.append("Category: ${jsonObject.getString("strCategory")}\n")
            mealDetails.append("Area: ${jsonObject.getString("strArea")}\n")
            mealDetails.append("Instructions: ${jsonObject.getString("strInstructions")}\n")
            mealDetails.append("Thumbnail: ${jsonObject.getString("strMealThumb")}\n")
            mealDetails.append("Tags: ${jsonObject.getString("strTags")}\n")
            mealDetails.append("Youtube: ${jsonObject.getString("strYoutube")}\n")
            for (i in 1..20) {
                val ingredientKey = "strIngredient$i"
                val ingredient = jsonObject.getString(ingredientKey)
                mealDetails.append("$ingredientKey: $ingredient\n")
            }
            for (i in 1..20) {
                val measureKey = "strMeasure$i"
                val measure = jsonObject.getString(measureKey)
                mealDetails.append("$measureKey: $measure\n")
            }

            mealDetails.append("Source: ${jsonObject.getString("strSource")}\n")
            mealDetails.append("Image Source: ${jsonObject.getString("strImageSource")}\n")
            mealDetails.append("Creative Common Confirmed: ${jsonObject.getString("strCreativeCommonsConfirmed")}\n")
            mealDetails.append("Date Modified: ${jsonObject.getString("dateModified")}\n")

            mealDetails.append("\n")
        }
        mealDetailsTextView.text = mealDetails
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
}