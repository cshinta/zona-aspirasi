package com.example.zonaaspirasi.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zonaaspirasi.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {
    private lateinit var SignUpMail: EditText
    private lateinit var SignUpPass: EditText
    private lateinit var SignUpConfirmPass: EditText
    private lateinit var SignUpNIK: EditText
    private lateinit var SignUpName: EditText
    private lateinit var SignUpPhone: EditText
    private lateinit var SignUpButton: Button
    private var auth: FirebaseAuth? = null
    lateinit var db: FirebaseFirestore
    lateinit var docReference : DocumentReference

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        SignUpMail = findViewById(R.id.emailInput)
        SignUpPass = findViewById(R.id.passInput)
        SignUpConfirmPass = findViewById(R.id.konfInput)
        SignUpName = findViewById(R.id.nameInput)
        SignUpPhone = findViewById(R.id.hpInput)
        SignUpNIK = findViewById(R.id.nikInput)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        SignUpButton = findViewById<View>(R.id.addAkun) as Button
        SignUpButton!!.setOnClickListener(View.OnClickListener {
            val email = SignUpMail.text.toString()
            val pass: String = SignUpPass.text.toString()
            val confirm: String = SignUpConfirmPass.text.toString()
            val name = SignUpName.text.toString()
            val phone: String = SignUpPhone.text.toString()
            val nik: String = SignUpNIK.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    applicationContext,
                    "Email wajib diisi",
                    Toast.LENGTH_LONG
                ).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(applicationContext, "Password wajib diisi", Toast.LENGTH_LONG)
                    .show()
            }
            if (pass.length == 0) {
                Toast.makeText(applicationContext, "Password wajib diisi", Toast.LENGTH_LONG)
                    .show()
            }
            if (pass.length < 8) {
                Toast.makeText(
                    applicationContext,
                    "Password harus memiliki minimal 8 karakter",
                    Toast.LENGTH_LONG
                ).show()
            }
            if (pass != confirm) {
                Toast.makeText(
                    applicationContext,
                    "Password harus sama",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                auth!!.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this,
                        OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Toast.makeText(this@RegisterActivity, "Registrasi Gagal", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                val firebaseUser: FirebaseUser? = auth!!.getCurrentUser()
                                val userID = firebaseUser!!.getUid()
                                FirebaseAuth.getInstance().signOut()
                                docReference = db.collection("users").document(userID)
                                val newUser = hashMapOf(
                                    "name" to name,
                                    "email" to email,
                                    "password" to pass,
                                    "nik" to nik,
                                    "phone" to phone,

                                )
                                docReference.set(newUser)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                                        startActivity(
                                            Intent(
                                                this@RegisterActivity,
                                                RegisterSuccess::class.java
                                            )
                                        )
                                        finish()
                                    }
                                    .addOnFailureListener { error ->
                                        Log.e("register", "Registrasi gagal, ${error.message}")
                                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                                    }


                            }
                        })
            }
        })
    }

    fun navigate_login(v: View?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}