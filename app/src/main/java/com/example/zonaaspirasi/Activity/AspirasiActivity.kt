package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.zonaaspirasi.R

class AspirasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspirasi)
    }
    fun navigate_add_aspirasi(v: View?) {
        val intent = Intent(this, AddAspirasiActivity::class.java)
        startActivity(intent)
    }

    fun navigate_add_laporan(v: View?) {
        val intent = Intent(this, AddLaporanActivity::class.java)
        startActivity(intent)
    }

    fun navigate_check_status(v: View?) {
        val intent = Intent(this, CheckStatusActivity::class.java)
        startActivity(intent)
    }
}