package com.example.zonaaspirasi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View;
import com.example.zonaaspirasi.Activity.HomeActivity
import com.example.zonaaspirasi.Activity.SplashActivity
import com.example.zonaaspirasi.Activity.StartActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Check current user
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    var authStateListener: FirebaseAuth.AuthStateListener = object :
        FirebaseAuth.AuthStateListener {
        override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
            val firebaseUser: FirebaseUser? = firebaseAuth.getCurrentUser()
            if (firebaseUser == null) {
                val intent = Intent(this@MainActivity, SplashActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (firebaseUser != null) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}