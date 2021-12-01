package com.example.zonaaspirasi.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import com.example.zonaaspirasi.R
import java.text.SimpleDateFormat
import java.util.*

class AddLaporanActivity : AppCompatActivity() {
    private lateinit var instansi: EditText
    private lateinit var lokasi: EditText
    private lateinit var dateButton: EditText
    private lateinit var sharedPreference: SharedPreferences
    private val PREF_NAME = "SharedLaporan"
    private val INSTANSI_KEY = "instansiTujuan"
    private val LOKASI_KEY = "lokasi"
    private val TANGGAL_KEY = "tanggal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_laporan)
        sharedPreference = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        instansi = findViewById(R.id.inputInstansi)
        dateButton = findViewById(R.id.inputDate)
        lokasi = findViewById(R.id.inputLokasi)

        val c = Calendar.getInstance()

        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, day)

                val formatDate = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(formatDate, Locale.US)
                dateButton.setText(sdf.format(c.time))

            }
        }

        dateButton.setInputType(InputType.TYPE_NULL)

        dateButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@AddLaporanActivity, dateSetListener, c.get(
                    Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        dateButton.setOnFocusChangeListener(View.OnFocusChangeListener { view, b ->
            if(b) {
                DatePickerDialog(this, dateSetListener, c.get(
                    Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        findViewById<Button>(R.id.button_next).setOnClickListener(View.OnClickListener {
            val intent = Intent(this@AddLaporanActivity, AddLaporanFinishActivity::class.java)
            startActivity(intent)

        })

        val instansiText = sharedPreference.getString(INSTANSI_KEY, "")
        val tanggalText = sharedPreference.getString(TANGGAL_KEY, "")
        val lokasiText = sharedPreference.getString(LOKASI_KEY, "")
        instansi.setText(instansiText)
        dateButton.setText(tanggalText)
        lokasi.setText(lokasiText)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(INSTANSI_KEY, instansi.text.toString())
        editor.putString(LOKASI_KEY, lokasi.text.toString())
        editor.putString(TANGGAL_KEY, dateButton.text.toString())
        editor.apply()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val instansiText = sharedPreference.getString(INSTANSI_KEY, "")
        val tanggalText = sharedPreference.getString(TANGGAL_KEY, "")
        val lokasiText = sharedPreference.getString(LOKASI_KEY, "")
        instansi.setText(instansiText)
        dateButton.setText(tanggalText)
        lokasi.setText(lokasiText)
    }
}