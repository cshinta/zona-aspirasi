package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.zonaaspirasi.R
import com.squareup.picasso.Picasso

class DetailAspirasiActivity : AppCompatActivity() {
    lateinit var tvJudul: TextView
    lateinit var tvDeskripsi: TextView
    lateinit var tvInstansi: TextView
    lateinit var tvKategori: TextView
    lateinit var imgDetail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_aspirasi)
        supportActionBar?.hide()

        tvJudul = findViewById(R.id.judulPengumuman)
        tvDeskripsi = findViewById(R.id.descField)
        tvInstansi = findViewById(R.id.instansiField)
        tvKategori = findViewById(R.id.kategoriField)
        imgDetail = findViewById(R.id.fotoPengumuman)

        //Get data from intent
        val judul = intent.getStringExtra("JUDUL")
        val deskripsi = intent.getStringExtra("DESKRIPSI")
        val instansi = intent.getStringExtra("INSTANSI")
        val kategori = intent.getStringExtra("KATEGORI")
        val imageURI = intent.getStringExtra("GAMBAR")

        tvJudul.text = judul
        tvDeskripsi.text = deskripsi
        tvInstansi.text = instansi
        tvKategori.text = kategori
        if(imageURI != ""){
            Picasso.get().load(imageURI).into(imgDetail)
        }
    }
}