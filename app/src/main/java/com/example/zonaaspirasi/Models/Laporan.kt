package com.example.zonaaspirasi.Models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

data class Laporan(
    val gambar: String?, val judul: String?, val deskripsi: String?, val tanggal: String?,
    val instansi: String?, val kategori: String?, val UID: String?, val lokasi: String?, val status: String?
) {
    constructor() : this("", "", "", "", "", "", "","","")
}