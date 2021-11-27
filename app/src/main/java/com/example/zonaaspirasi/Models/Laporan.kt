package com.example.zonaaspirasi.Models

data class Laporan(
    val gambar: String?, val judul: String?, val deskripsi: String?, val tanggal: String?,
    val instansi: String?, val kategori: String?, val penulis: String?
) {
    constructor() : this("", "", "", "", "", "", "")
}