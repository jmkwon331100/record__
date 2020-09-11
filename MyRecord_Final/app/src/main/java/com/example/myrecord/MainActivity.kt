package com.example.myrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val myIntent = Intent(this, RecomActivity::class.java)
            startActivity(myIntent)
        }

        button2.setOnClickListener {
            val myIntent2 = Intent(this,MemoActivity::class.java)
            startActivity(myIntent2)
        }

        button3.setOnClickListener {
            val myIntent3 = Intent(this, SearchActivity::class.java)
            startActivity(myIntent3)
        }

        button5.setOnClickListener {
            val myIntent5 = Intent(this, RandomActivity::class.java)
            startActivity(myIntent5)
        }

        button6.setOnClickListener {
            val myIntent5 = Intent(this, MapActivity::class.java)
            startActivity(myIntent5)
        }


        intent.getStringExtra(LoginActivity.USER_NAME).run {
            text_msg.text=this+"의 냉장고"
        }
    }
}