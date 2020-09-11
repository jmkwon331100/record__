package com.example.myrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bottomnavi.RecipeData
import kotlinx.android.synthetic.main.activity_recipe2.*
import kotlinx.android.synthetic.main.list_item_recipe.*

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe2)

        val recipeName = intent.getStringExtra(RECIPE)

        val recipeData = RecipeData.valueOf(recipeName)

        editText4.text=recipeData.recipe
        et_recipe_ingre.text=recipeData.ingre
        et_recipe_name.text=recipeData.recipeName

        back_btn.setOnClickListener{
            finish()
        }
    }

    companion object{
        const val RECIPE="RECIPE"
    }
}
