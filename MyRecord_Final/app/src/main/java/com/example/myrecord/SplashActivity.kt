package com.example.myrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemClock.sleep(200)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}