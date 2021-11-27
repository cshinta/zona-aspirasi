package com.example.zonaaspirasi.Models

data class Aspirasi(
    val gambar: String?, val judul: String?, val deskripsi: String?,
    val instansi: String?, val kategori: String?, val penulis: String?
) {
    constructor() : this("", "", "", "", "", "")
}