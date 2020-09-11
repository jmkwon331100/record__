package com.example.myrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_recipe2.*
import kotlin.random.Random

class RandomActivity : AppCompatActivity() {


    private lateinit var recipeTextView: TextView
    private lateinit var recipeImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        recipeImageView = findViewById(R.id.random_image)

        back_btn.setOnClickListener{
            finish()
        }
    }

    fun onRollClick(v: View){
        var randomNumber = Random.nextInt(9)


        when (randomNumber){
            1 -> recipeImageView.setImageResource(R.drawable.workout11)
            2 -> recipeImageView.setImageResource(R.drawable.workout21)
            3 -> recipeImageView.setImageResource(R.drawable.workout31)
            4 -> recipeImageView.setImageResource(R.drawable.workout41)
            5 -> recipeImageView.setImageResource(R.drawable.workout51)
            6 -> recipeImageView.setImageResource(R.drawable.workout61)
            7 -> recipeImageView.setImageResource(R.drawable.workout71)
            8 -> recipeImageView.setImageResource(R.drawable.workout81)
            9 -> recipeImageView.setImageResource(R.drawable.workout9)

        }



    }


}