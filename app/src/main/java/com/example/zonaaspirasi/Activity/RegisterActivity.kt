package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull

import android.text.TextUtils

import com.example.zonaaspirasi.R


class RegisterActivity : AppCompatActivity() {
    private lateinit var SignUpMail: EditText
    private lateinit var SignUpPass: EditText
    private lateinit var SignUpConfirmPass: EditText
    private lateinit var SignUpButton: Button
    private var auth: FirebaseAuth? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        SignUpMail = findViewById(R.id.emailInput)
        SignUpPass = findViewById(R.id.passInput)
        SignUpConfirmPass = findViewById(R.id.konfInput)
        auth = FirebaseAuth.getInstance()
        SignUpButton = findViewById<View>(R.id.addAkun) as Button
        SignUpButton!!.setOnClickListener(View.OnClickListener {
            val email = SignUpMail.text.toString()
            val pass: String = SignUpPass.text.toString()
            val confirm: String = SignUpConfirmPass.text.toString()
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
                    .addOnCompleteListener(this@RegisterActivity,
                        OnCompleteListener<AuthResult?> { task ->
                            if (!task.isSuccessful) {
                                Toast.makeText(this@RegisterActivity, "ERROR", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        HomeActivity::class.java
                                    )
                                )
                                finish()
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