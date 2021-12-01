package com.example.zonaaspirasi.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.zonaaspirasi.R
import com.google.firebase.auth.AuthResult

class AddAspirasiActivity : AppCompatActivity() {
    private lateinit var instansi: EditText
    private lateinit var autoTextView: AutoCompleteTextView
    private lateinit var sharedPreference: SharedPreferences
    private val PREF_NAME = "SharedAspirasi"
    private val INSTANSI_KEY = "instansiTujuan"
    private val KATEGORI_KEY = "kategoriAspirasi"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_aspirasi)
        sharedPreference = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        instansi = findViewById(R.id.inputInstansi)
        autoTextView = findViewById(R.id.inputKategori)
        val options = resources.getStringArray(R.array.kategoriAspirasi)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, options
        )
        autoTextView.setAdapter(adapter)

        findViewById<Button>(R.id.button_next).setOnClickListener(View.OnClickListener {
            val intent = Intent(this@AddAspirasiActivity, AddAspirasiFinishActivity::class.java)
            startActivity(intent)
        })

        val instansiText = sharedPreference.getString(INSTANSI_KEY, "")
        val kategoriText = sharedPreference.getString(KATEGORI_KEY, "")
        instansi.setText(instansiText)
        autoTextView.setText(kategoriText)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(INSTANSI_KEY, instansi.text.toString())
        editor.putString(KATEGORI_KEY, autoTextView.text.toString())
        editor.apply()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val instansiText = sharedPreference.getString(INSTANSI_KEY, "")
        val kategoriText = sharedPreference.getString(KATEGORI_KEY, "")
        instansi.setText(instansiText)
        autoTextView.setText(kategoriText)
    }


}