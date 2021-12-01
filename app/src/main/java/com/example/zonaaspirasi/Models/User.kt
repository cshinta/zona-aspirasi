package com.example.zonaaspirasi.Models

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

data class User(val email: String?, val nik: String?, val name: String?, val phone: String?, val image: String?, val password: String?) {
    companion object {
        fun DocumentSnapshot.toUser(): User? {
            try {
                val email = getString("email")!!
                val nik = getString("nik")!!
                val name = getString("name")!!
                val phone = getString("phone")!!
                val image = getString("image")!!
                val password = getString("password")
                return User(email, nik, name, phone, image, password)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting user profile", e)
                return null
            }
        }
        private const val TAG = "User"
    }
}