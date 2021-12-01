package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.zonaaspirasi.R
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetailPengumumanActivity : AppCompatActivity() {
    lateinit var tvJudul: TextView
    lateinit var tvDeskripsi: TextView
    lateinit var tvTanggal: TextView
    lateinit var imgDetail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengumuman)
        supportActionBar?.hide()

        tvJudul = findViewById(R.id.judulPengumuman)
        tvDeskripsi = findViewById(R.id.descPengumuman)
        tvTanggal = findViewById(R.id.datePengumuman)
        imgDetail = findViewById(R.id.fotoPengumuman)

        //Get data from intent
       val judul = intent.getStringExtra("JUDUL")
        val tanggal = intent.getStringExtra("TANGGAL")
        val desc = intent.getStringExtra("DESKRIPSI")
        val img = intent.getStringExtra("GAMBAR")

        val db = FirebaseFirestore.getInstance()

        tvJudul.text = judul
        tvTanggal.text = tanggal
        tvDeskripsi.text = desc
        Picasso.get().load(img).into(imgDetail)
    }
}