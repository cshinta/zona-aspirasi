package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.zonaaspirasi.R
import com.google.firebase.auth.FirebaseAuth

class RegisterSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_success)
        FirebaseAuth.getInstance().signOut()

        findViewById<Button>(R.id.addAkun).setOnClickListener{
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}