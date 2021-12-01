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
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.*

class AddAspirasiFinishActivity : AppCompatActivity() {
    private lateinit var judul: EditText
    private lateinit var isi: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnImage: Button
    private lateinit var imgShow: ImageView
    private lateinit var docReference : DocumentReference
    private lateinit var sharedPreference: SharedPreferences

    private val PREF_NAME = "SharedAspirasi"
    private val JUDUL_KEY = "judulAspirasi"
    private val ISI_KEY = "isiAspirasi"
    private val IMAGE_KEY = "imageAspirasi"
    private val INSTANSI_KEY = "instansiTujuan"
    private val KATEGORI_KEY = "kategoriAspirasi"

    private var auth: FirebaseAuth? = null
    lateinit var db : FirebaseFirestore
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    var imageURI =""
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_aspirasi_finish)
        supportActionBar?.hide()
        sharedPreference = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        judul = findViewById(R.id.inputJudul)
        isi = findViewById(R.id.inputIsi)
        btnSubmit = findViewById(R.id.button_send)
        btnImage = findViewById(R.id.btn_upload_img)
        imgShow = findViewById(R.id.img_showUpload)

        db = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        btnImage.setOnClickListener(){
            launchGallery()
        }

        btnSubmit.setOnClickListener(View.OnClickListener {
            var judulText = judul.text.toString()
            var isiText = isi.text.toString()
            if(judulText!=null && isiText!=null){
                addData(judulText,isiText)
            }else{
                Log.e("Post", "Data is not valid")
            }
        })

        val judulText = sharedPreference.getString(JUDUL_KEY, "")
        val isiText = sharedPreference.getString(ISI_KEY, "")
        judul.setText(judulText)
        isi.setText(isiText)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(JUDUL_KEY, judul.text.toString())
        editor.putString(ISI_KEY, isi.text.toString())
        editor.apply()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val judulText = sharedPreference.getString(JUDUL_KEY, "")
        val isiText = sharedPreference.getString(ISI_KEY, "")
        judul.setText(judulText)
        isi.setText(isiText)
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun uploadImage(){
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            Log.i("Image","Uploading")

            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                        Log.e("Image","Error, $it")
                    }
                }
                Log.i("Image","Uploading Here")
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    imageURI = downloadUri.toString()
                    Log.i("Image", "${downloadUri.toString()}")
                } else {
                    Log.e("Image", "Gagal upload")
                }
            }?.addOnFailureListener{
                Log.e("Image", "Error, $it")
            }
        }else{
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgShow.setImageBitmap(bitmap)
                uploadImage()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun addData(judul:String,isi:String){
        val instansiText = sharedPreference.getString(INSTANSI_KEY, "")
        val kategoriText = sharedPreference.getString(KATEGORI_KEY, "")

        auth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = auth!!.getCurrentUser()
        val userID = firebaseUser!!.getUid()

        val newPost = hashMapOf(
            "judul" to judul,
            "deskripsi" to isi,
            "gambar" to imageURI,
            "kategori" to kategoriText,
            "instansi" to instansiText,
            "UID" to userID,
        )

        db.collection("aspirasi").add(newPost)
            .addOnSuccessListener {
                Log.i("Posting","Postingan berhasil disimpan")
                val editor: SharedPreferences.Editor = sharedPreference.edit().clear()
                editor.apply()

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