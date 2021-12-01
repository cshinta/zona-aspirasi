package com.example.zonaaspirasi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import com.google.android.gms.tasks.OnCompleteListener
import com.example.zonaaspirasi.R

class LoginActivity : AppCompatActivity() {
    private lateinit var SignInMail: EditText
    private lateinit var SignInPass: EditText
    private lateinit var MailAlert: TextView
    private lateinit var PassAlert: TextView
    private lateinit var SignInButton: Button
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()
        // set the view now
        setContentView(R.layout.activity_login)

        SignInMail = findViewById(R.id.emailInput)
        SignInPass = findViewById(R.id.passInput)
        MailAlert = findViewById(R.id.text_email_alert)
        PassAlert = findViewById(R.id.text_password_alert)
        SignInButton = findViewById<View>(R.id.addAkun) as Button

        MailAlert.setVisibility(View.GONE);
        PassAlert.setVisibility(View.GONE);
        SignInButton.setEnabled(false);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()
        SignInButton.setOnClickListener(View.OnClickListener {
            val email = SignInMail.text.toString()
            val password = SignInPass.text.toString()

            //authenticate user
            auth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity,
                    OnCompleteListener<AuthResult?> { task ->
                        if (!task.isSuccessful) {
                            // there was an error

                                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
                                    .show()

                        } else {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
        })

        supportActionBar?.hide()
    }

    fun navigate_register(v: View?) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}