package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.zonaaspirasi.R

class AddSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_success)

        supportActionBar?.hide()
    }
    fun navigateBack(v: View?) {
        super.onBackPressed();
    }

}