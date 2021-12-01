package com.example.zonaaspirasi.Models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

data class PengumumanCoba(
    val gambar: String?, val judul: String?, val deskripsi: String?, val tanggal: String?,
) {
    constructor() : this("", "", "", "")
}