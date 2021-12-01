package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.zonaaspirasi.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<Button>(R.id.btn_mulai).setOnClickListener{
            intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.hide()
    }

}