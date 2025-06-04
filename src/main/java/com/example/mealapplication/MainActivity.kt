package com.example.mealapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mealapplication.database.MealDatabase
import com.example.mealapplication.database.MealRepository
import com.example.mealapplication.entities.Meal
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var mealRepository: MealRepository
    private var REQUEST_CODE = 0
    private val mealsList = listOf(
        Meal(
            mealId = null,
            meal = "Sweet and Sour Pork",
            drinkAlternate = null,
            category = "Pork",
            area = "Chinese",
            instructions = "Preparation\\r\\n1. Crack the egg into a bowl. Separate the egg white and yolk.\\r\\n\\r\\nSweet and Sour Pork\\r\\n2. Slice the pork tenderloin into ips.\\r\\n\\r\\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and an egg white.\\r\\n\\r\\n4. Marinade the pork ips for about 20 minutes.\\r\\n\\r\\n5. Put the remaining starch in a bowl. Add some water and vinegar to make a starchy sauce.\\r\\n\\r\\nSweet and Sour Pork\\r\\nCooking Inuctions\\r\\n1. Pour the cooking oil into a wok and heat to 190\\u00b0C (375\\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\\r\\n\\r\\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\\r\\n\\r\\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\\r\\n\\r\\n4. Pour in the starchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\\r\\n\\r\\n5. Serve on a plate and add some coriander for decoration.",
            mealThumb = "https://www.themealdb.com/images/media/meals/1529442316.jpg",
            tags = "Sweet",
            youtube = "https://www.youtube.com/watch?v=mdaBIhgEAMo",
            ingredient1 = "Pork",
            ingredient2 = "Egg",
            ingredient3 = "Water",
            ingredient4 = "Salt",
            ingredient5 = "Sugar",
            ingredient6 = "Soy Sauce",
            ingredient7 = "Starch",
            ingredient8 = "Tomato Puree",
            ingredient9 = "Vinegar",
            ingredient10 = "Coriander",
            ingredient11 = null,
            ingredient12 = null,
            ingredient13 = null,
            ingredient14 = null,
            ingredient15 = null,
            ingredient16 = null,
            ingredient17 = null,
            ingredient18 = null,
            ingredient19 = null,
            ingredient20 = null,
            measure1 = "200g",
            measure2 = "1",
            measure3 = "Dash",
            measure4 = "1/2 tsp",
            measure5 = "1 tsp ",
            measure6 = "10g",
            measure7 = "10g",
            measure8 = "30g",
            measure9 = "10g",
            measure10 = "Dash",
            measure11 = null,
            measure12 = null,
            measure13 = null,
            measure14 = null,
            measure15 = null,
            measure16 = null,
            measure17 = null,
            measure18 = null,
            measure19 = null,
            measure20 = null,
            source = null,
            imageSource = null,
            creativeCommonsConfirmed = null,
            dateModified = null
        ),
        Meal(
            mealId = null,
            meal = "Chicken Marengo",
            drinkAlternate = null,
            category = "Chicken",
            area = "French",
            instructions = "Heat the oil in a large flameproof casserole dish and stir-fry the mushrooms until they start to soften. Add the chicken legs and cook briefly on each side to colour them a little.\\r\\nPour in the passata, crumble in the stock cube and stir in the olives. Season with black pepper \\u2013 you shouldn\\u2019t need salt. Cover and simmer for 40 mins until the chicken is tender. Sprinkle with parsley and serve with pasta and a salad, or mash and green veg, if you like.",
            mealThumb = "https://www.themealdb.com/images/media/meals/qpxvuq1511798906.jpg",
            tags = null,
            youtube = "https://www.youtube.com/watch?v=U33HYUr-0Fw",
            ingredient1 = "Olive Oil",
            ingredient2 = "Mushrooms",
            ingredient3 = "Chicken Legs",
            ingredient4 = "Passata",
            ingredient5 = "Chicken Stock Cube",
            ingredient6 = "Black Olives",
            ingredient7 = "Parsley",
            ingredient8 = null,
            ingredient9 = null,
            ingredient10 = null,
            ingredient11 = null,
            ingredient12 = null,
            ingredient13 = null,
            ingredient14 = null,
            ingredient15 = null,
            ingredient16 = null,
            ingredient17 = null,
            ingredient18 = null,
            ingredient19 = null,
            ingredient20 = null,
            measure1 = "1 tbs",
            measure2 = "300g",
            measure3 = "4",
            measure4 = "500g",
            measure5 = "1",
            measure6 = "100g",
            measure7 = "Chopped",
            measure8 = null,
            measure9 = null,
            measure10 = null,
            measure11 = null,
            measure12 = null,
            measure13 = null,
            measure14 = null,
            measure15 = null,
            measure16 = null,
            measure17 = null,
            measure18 = null,
            measure19 = null,
            measure20 = null,
            source = "https://www.bbcgoodfood.com/recipes/3146682/chicken-marengo",
            imageSource = null,
            creativeCommonsConfirmed = null,
            dateModified = null
        ),
        Meal(
            mealId = null,
            meal = "Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
            drinkAlternate = null,
            category = "Beef",
            area = "Vietnamese",
            instructions = "Add'l ingredients: mayonnaise, siracha\\r\\n\\r\\n1\\r\\n\\r\\nPlace rice in a fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, then cover and reduce heat to low. Cook until rice is tender, 15 minutes. Keep covered off heat for at least 10 minutes or until ready to serve.\\r\\n\\r\\n2\\r\\n\\r\\nMeanwhile, wash and dry all produce. Peel and finely chop garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. Halve, peel, and medium dice onion. Trim, peel, and grate carrot.\\r\\n\\r\\n3\\r\\n\\r\\nIn a medium bowl, combine cucumber, juice from half the lime, \\u00bc tsp sugar (\\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much sriracha as you\\u2019d like. Season with salt and pepper.\\r\\n\\r\\n4\\r\\n\\r\\nHeat a drizzle of oil in a large pan over medium-high heat. Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. Turn off heat; taste and season with salt and pepper.\\r\\n\\r\\n5\\r\\n\\r\\nFluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice. Drizzle with sriracha mayo.",
            mealThumb = "https://www.themealdb.com/images/media/meals/z0ageb1583189517.jpg",
            tags = null,
            youtube = "",
            ingredient1 = "Rice",
            ingredient2 = "Onion",
            ingredient3 = "Lime",
            ingredient4 = "Garlic Clove",
            ingredient5 = "Cucumber",
            ingredient6 = "Carrots",
            ingredient7 = "Ground Beef",
            ingredient8 = "Soy Sauce",
            ingredient9 = null,
            ingredient10 = null,
            ingredient11 = null,
            ingredient12 = null,
            ingredient13 = null,
            ingredient14 = null,
            ingredient15 = null,
            ingredient16 = null,
            ingredient17 = null,
            ingredient18 = null,
            ingredient19 = null,
            ingredient20 = null,
            measure1 = "White",
            measure2 = "1",
            measure3 = "1",
            measure4 = "3",
            measure5 = "1",
            measure6 = "3 oz",
            measure7 = "1 lb",
            measure8 = "2 oz",
            measure9 = null,
            measure10 = null,
            measure11 = null,
            measure12 = null,
            measure13 = null,
            measure14 = null,
            measure15 = null,
            measure16 = null,
            measure17 = null,
            measure18 = null,
            measure19 = null,
            measure20 = null,
            source = "",
            imageSource = null,
            creativeCommonsConfirmed = null,
            dateModified = null
        ),
        Meal(
            mealId = null,
            meal = "Leblebi Soup",
            drinkAlternate = null,
            category = "Vegetarian",
            area = "Tunisian",
            instructions = "Heat the oil in a large pot. Add the onion and cook until translucent.\\r\\nDrain the soaked chickpeas and add them to the pot together with the vegetable stock. Bring to the boil, then reduce the heat and cover. Simmer for 30 minutes.\\r\\nIn the meantime toast the cumin in a small ungreased frying pan, then grind them in a mortar. Add the garlic and salt and pound to a fine paste.\\r\\nAdd the paste and the harissa to the soup and simmer until the chickpeas are tender, about 30 minutes.\\r\\nSeason to taste with salt, pepper and lemon juice and serve hot.",
            mealThumb = "https://www.themealdb.com/images/media/meals/x2fw9e1560460636.jpg",
            tags = "Soup",
            youtube = "https://www.youtube.com/watch?v=BgRifcCwinY",
            ingredient1 = "Olive Oil",
            ingredient2 = "Onion",
            ingredient3 = "Chickpeas",
            ingredient4 = "Vegetable Stock",
            ingredient5 = "Cumin",
            ingredient6 = "Garlic",
            ingredient7 = "Salt",
            ingredient8 = "Harissa Spice",
            ingredient9 = "Pepper",
            ingredient10 = "Lime",
            ingredient11 = null,
            ingredient12 = null,
            ingredient13 = null,
            ingredient14 = null,
            ingredient15 = null,
            ingredient16 = null,
            ingredient17 = null,
            ingredient18 = null,
            ingredient19 = null,
            ingredient20 = null,
            measure1 = "2 tbs",
            measure2 = "1 medium finely diced",
            measure3 = "250g",
            measure4 = "1.5L",
            measure5 = "1 tsp",
            measure6 = "5 cloves",
            measure7 = "1/2 tsp",
            measure8 = "1 tsp",
            measure9 = "Pinch",
            measure10 = "1/2",
            measure11 = null,
            measure12 = null,
            measure13 = null,
            measure14 = null,
            measure15 = null,
            measure16 = null,
            measure17 = null,
            measure18 = null,
            measure19 = null,
            measure20 = null,
            source = "http://allrecipes.co.uk/recipe/43419/leblebi--tunisian-chickpea-soup-.aspx",
            imageSource = null,
            creativeCommonsConfirmed = null,
            dateModified = null
        ),
    )

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val addMealsButton = findViewById<Button>(R.id.add_meals_button)
        val searchByIngredientButton = findViewById<Button>(R.id.search_by_ingredient_button)
        val searchMealsButton = findViewById<Button>(R.id.search_for_meals_button)
        val searchByMealButton = findViewById<Button>(R.id.search_by_name_button)

        addMealsButton.setOnClickListener {
            mealRepository = MealRepository(this)
            GlobalScope.launch {
                mealsList.forEach { meal ->
                    mealRepository.addMeal(meal)
                }
            }
            Toast.makeText(this, "Successfully saved to database", Toast.LENGTH_SHORT).show()
        }

        searchByIngredientButton.setOnClickListener {
            val intent = Intent(this, SearchForMealsByIngredients::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        searchMealsButton.setOnClickListener {
            val intent = Intent(this, SearchForMeals::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        searchByMealButton.setOnClickListener {
            val intent = Intent(this, SearchForMealsByName::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}