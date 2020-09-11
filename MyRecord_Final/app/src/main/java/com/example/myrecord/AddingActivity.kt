package com.example.myrecord

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_adding.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class AddingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)

        button10.setOnClickListener {
            //id입력여부 체크
            if((!textView10.text.toString().isNullOrBlank())){
                val myIntent2 = Intent(this, SearchActivity::class.java)
                myIntent2.putExtra(USER_ADD,textView10.text.toString())
                startActivity(myIntent2)
            } else {
                Toast.makeText(this,"추가할재료를 입력해주세요.", Toast.LENGTH_LONG).show()
            }
        }




    }

    companion object {
        const val USER_ADD = "USER_ADD"
    }
}