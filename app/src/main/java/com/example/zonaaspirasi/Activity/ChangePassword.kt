package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.zonaaspirasi.R

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        findViewById<Button>(R.id.buttonsimpan).setOnClickListener{
            intent = Intent(this, SuccessPassword::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
    }
    fun navigateBack(v: View?) {
        super.onBackPressed();
    }
}