package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zonaaspirasi.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.zonaaspirasi.MainActivity

import android.content.Intent
import android.view.View

import com.google.firebase.auth.FirebaseAuth




class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_home, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()
    }

    fun navigateLogOut(v: View?) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    fun navigate_aspirasi(v: View?) {
        val intent = Intent(this, AspirasiActivity::class.java)
        startActivity(intent)
    }

    fun navigate_pengumuman(v: View?) {
        val intent = Intent(this, PengumumanActivity::class.java)
        startActivity(intent)
    }

    fun navigate_detail_profile(v: View?) {
        val intent = Intent(this, ProfileDetailActivity::class.java)
        startActivity(intent)
    }

    fun navigate_change_password(v: View?) {
        val intent = Intent(this, ChangePassword::class.java)
        startActivity(intent)
    }

    fun navigasi_edit_profile(v: View?) {
        val intent = Intent(this, EditProfile::class.java)
        startActivity(intent)
    }

}