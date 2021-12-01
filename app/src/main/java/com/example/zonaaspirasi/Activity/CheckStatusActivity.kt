package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.zonaaspirasi.Adapter.CheckStatusFragmentAdapter
import com.example.zonaaspirasi.R
import com.google.android.material.tabs.TabLayout

class CheckStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_status)

        val viewPager = findViewById<ViewPager>(R.id.viewpager_main)

        viewPager.adapter = CheckStatusFragmentAdapter(supportFragmentManager)

        val tabs = findViewById<TabLayout>(R.id.tabs_status)

        tabs.setupWithViewPager(viewPager)
    }
}