package com.example.zonaaspirasi.Models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

data class Aspirasi(
    val gambar: String?, val judul: String?, val deskripsi: String?,
    val instansi: String?, val kategori: String?, val UID: String?
) {
    constructor() : this("", "", "", "","","")
}