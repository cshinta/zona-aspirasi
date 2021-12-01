package com.example.zonaaspirasi.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.zonaaspirasi.Activity.AspirasiStatusFragment
import com.example.zonaaspirasi.Activity.LaporanStatusFragment


class CheckStatusFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        AspirasiStatusFragment(),
        LaporanStatusFragment()
    )

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Aspirasi"
            else -> "Laporan"
        }
    }
}