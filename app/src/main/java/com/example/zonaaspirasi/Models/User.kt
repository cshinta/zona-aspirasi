package com.example.zonaaspirasi.Models

data class User(val email: String?, val nik: String?, val name: String?, val phone: String?) {
    constructor() : this("", "", "", "")
}