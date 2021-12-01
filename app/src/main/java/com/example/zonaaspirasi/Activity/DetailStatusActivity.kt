package com.example.zonaaspirasi.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.zonaaspirasi.R
import com.squareup.picasso.Picasso

class DetailStatusActivity : AppCompatActivity() {
    lateinit var tvJudul: TextView
    lateinit var tvDeskripsi: TextView
    lateinit var tvInstansi: TextView
    lateinit var tvKategori: TextView
    lateinit var tvTanggal: TextView
    lateinit var tvLokasi: TextView
    lateinit var tvStatus: TextView
    lateinit var tvCardStatus : CardView
    lateinit var imgDetail: ImageView
    lateinit var buttonHapus : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_status)
        supportActionBar?.hide()

        tvJudul = findViewById(R.id.judulPengumuman)
        tvDeskripsi = findViewById(R.id.descField)
        tvInstansi = findViewById(R.id.instansiField)
        tvKategori = findViewById(R.id.kategoriField)
        tvTanggal = findViewById(R.id.dateField)
        tvLokasi = findViewById(R.id.locationField)
        tvStatus = findViewById(R.id.statusField)
        tvCardStatus = findViewById(R.id.statusColor)
        imgDetail = findViewById(R.id.fotoPengumuman)
        buttonHapus = findViewById(R.id.btn_hapus)

        //Get data from intent
        val judul = intent.getStringExtra("JUDUL")
        val deskripsi = intent.getStringExtra("DESKRIPSI")
        val instansi = intent.getStringExtra("INSTANSI")
        val kategori = intent.getStringExtra("KATEGORI")
        val tanggal = intent.getStringExtra("TANGGAL")
        val lokasi = intent.getStringExtra("LOKASI")
        val status = intent.getStringExtra("STATUS")
        val imageURI = intent.getStringExtra("GAMBAR")

        tvJudul.text = judul
        tvDeskripsi.text = deskripsi
        tvInstansi.text = instansi
        tvKategori.text = kategori
        tvTanggal.text = tanggal
        tvLokasi.text = lokasi
        tvStatus.text = status
        if(status == "Menunggu"){
            tvCardStatus.setCardBackgroundColor(Color.parseColor("#FA1E1E"))
            buttonHapus.setVisibility(View.VISIBLE)
        }
        if(status == "Proses"){
            tvCardStatus.setCardBackgroundColor(Color.parseColor("#FF9211"))
            buttonHapus.setVisibility(View.GONE)
        }
        if(status == "Selesai"){
            tvCardStatus.setCardBackgroundColor(Color.parseColor("#51F305"))
            buttonHapus.setVisibility(View.GONE)
        }
        if(imageURI != ""){
            Picasso.get().load(imageURI).into(imgDetail)
        }
        supportActionBar?.hide()
    }

}