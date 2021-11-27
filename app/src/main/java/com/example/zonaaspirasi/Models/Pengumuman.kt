package com.example.zonaaspirasi.Models

data class Pengumuman(
    val gambar: String?, val judul: String?, val deskripsi: String?, val tanggal: String?,
) {
    constructor() : this("", "", "", "")
}