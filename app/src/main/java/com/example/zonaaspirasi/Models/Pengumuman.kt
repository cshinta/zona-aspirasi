package com.example.zonaaspirasi.Models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

data class Pengumuman(
    val gambar: String?, val judul: String?, val deskripsi: String?, val tanggal: String?,
) {
    companion object {
        fun DocumentSnapshot.toPengumuman(): Pengumuman? {
            try {
                val gambar = getString("gambar")!!
                val judul = getString("judul")!!
                val deskripsi = getString("deskripsi")!!
                val tanggal = getString("tanggal")!!
                return Pengumuman(gambar, judul, deskripsi, tanggal)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting pengumuman", e)
                return null
            }
        }
        private const val TAG = "Pengumuman"
    }
}