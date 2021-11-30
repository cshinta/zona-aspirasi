package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.zonaaspirasi.R

class AddLaporanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_laporan)

        val fragPart1 = AddLaporanPart1()
        val fragPart2 = AddLaporanPart2()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_main, fragPart1)
            commit()
        }
        findViewById<Button>(R.id.button_next).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_main,
                    fragPart2)
                findViewById<Button>(R.id.button_next).setText("Kirim")
                commit()

            }
        }
    }
}