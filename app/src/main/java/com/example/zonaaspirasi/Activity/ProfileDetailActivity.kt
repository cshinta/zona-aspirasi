package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.zonaaspirasi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ProfileDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        supportActionBar?.hide()

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val firebaseUser: FirebaseUser? = auth!!.getCurrentUser()
        val userID = firebaseUser!!.getUid()
        db.collection("users").document(userID).get()
            .addOnSuccessListener { document ->
                Log.i("userData", "${document}")
                val displayName = document["name"].toString()
                val nik = document["nik"].toString()
                val gambar = document["image"].toString()
                val email = document["email"].toString()
                val phone = document["phone"].toString()
                findViewById<TextView>(R.id.NamaUser).text = displayName
                findViewById<TextView>(R.id.NIKUser).text = nik
                findViewById<TextView>(R.id.EmailUser).text = email
                findViewById<TextView>(R.id.NomorTeleponUser).text = phone
                Picasso.get().load(gambar).resize(200, 200).transform(CropCircleTransformation()).into(findViewById<ImageView>(R.id.fotoprofil))
            }
            .addOnFailureListener {exception ->
                Log.e("profil", "Profil Error, ${exception}")
            }
    }
    fun navigateBack(v: View?) {
        super.onBackPressed();
    }
}