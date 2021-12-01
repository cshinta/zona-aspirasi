package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.zonaaspirasi.R

class SuccessPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_password)

        findViewById<Button>(R.id.buttonlogin).setOnClickListener{
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
    }

}