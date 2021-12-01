package com.example.zonaaspirasi.Activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.example.zonaaspirasi.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.IOException
import java.util.*

class EditProfile : AppCompatActivity() {
    private lateinit var nama: EditText
    private lateinit var phone: EditText
    private lateinit var nik: EditText
    private lateinit var email: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnImage: ImageView

    private var auth: FirebaseAuth? = null
    lateinit var db : FirebaseFirestore
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    var imageURI =""
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        nama = findViewById<EditText>(R.id.editTextNamaLengkap)
        nik = findViewById<EditText>(R.id.editTextNIK)
        email = findViewById<EditText>(R.id.editTextEmail)
        phone = findViewById<EditText>(R.id.editTextNotlp)
        btnSubmit = findViewById<Button>(R.id.buttonsimpan)
        btnImage = findViewById<ImageView>(R.id.imageButtonFotoProfil)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
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
                findViewById<EditText>(R.id.editTextNamaLengkap).setText(displayName)
                findViewById<EditText>(R.id.editTextNIK).setText(nik)
                findViewById<EditText>(R.id.editTextEmail).setText(email)
                findViewById<EditText>(R.id.editTextNotlp).setText(phone)
                Picasso.get().load(gambar).resize(300, 300).transform(CropCircleTransformation()).into(findViewById<ImageView>(R.id.imageButtonFotoProfil))
            }
            .addOnFailureListener {exception ->
                Log.e("login", "Login Error, ${exception}")
            }

        btnSubmit.setOnClickListener(View.OnClickListener {
            var namaText = nama.text.toString()
            var nikText = nik.text.toString()
            var phoneText = phone.text.toString()
            var emailText = email.text.toString()
            if(namaText!=null && nikText!=null && phoneText!=null && emailText!=null){
                addData(namaText, nikText, phoneText, emailText)
            }else{
                Log.e("Profile", "Data is not valid")
            }
        })
    }

    fun addData(nama:String,nik:String,phone:String,email:String){
        auth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = auth!!.getCurrentUser()
        val userID = firebaseUser!!.getUid()

        val newPost = hashMapOf(
            "name" to nama,
            "email" to email,
            "nik" to nik,
            "phone" to phone,
            "image" to "https://firebasestorage.googleapis.com/v0/b/zona-aspirasi.appspot.com/o/uploads%2Fblank-profile-picture-973460_640.webp?alt=media&token=ec6cd48d-542c-4416-8632-055809f0ad35"
        )

        db.collection("users").document(userID).set(newPost)
            .addOnSuccessListener {
                Log.i("Posting","Postingan berhasil disimpan")

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            .addOnFailureListener{ error ->
                Log.e("Error","Postingan gagal disimpan ${error.message}")
            }

    }
    fun navigateBack(v: View?) {
        super.onBackPressed();
    }
}