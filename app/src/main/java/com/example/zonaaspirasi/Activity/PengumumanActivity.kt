package com.example.zonaaspirasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zonaaspirasi.Adapter.PengumumanAdapter
import com.example.zonaaspirasi.Adapter.PengumumanAdapter2
import com.example.zonaaspirasi.Models.Pengumuman
import com.example.zonaaspirasi.Models.PengumumanCoba
import com.example.zonaaspirasi.R
import com.example.zonaaspirasi.Utils.PengumumanRepo

class PengumumanActivity : AppCompatActivity() {

    lateinit var rvPosting: RecyclerView
    private lateinit var  pengumumanRepo : PengumumanRepo
    private var postList : MutableList<PengumumanCoba> = ArrayList()
    private var pengumumanAdapter : PengumumanAdapter2 = PengumumanAdapter2(postList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        pengumumanRepo = PengumumanRepo()

        loadPostData()
        rvPosting = findViewById(R.id.pengumuman_rv)
        rvPosting.layoutManager = LinearLayoutManager(this)
        rvPosting.adapter = pengumumanAdapter

        supportActionBar?.hide()
    }

    private fun loadPostData(){
        pengumumanRepo.getPengumumanlist()
            .addOnCompleteListener{
                postList = it.result!!.toObjects(PengumumanCoba::class.java)
                if(postList.size > 0){
                    findViewById<LinearLayout>(R.id.emptyLayout).setVisibility(View.GONE)
                }
                else{
                    findViewById<LinearLayout>(R.id.emptyLayout).setVisibility(View.VISIBLE)
                }
                pengumumanAdapter.postListItems= postList
                pengumumanAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener{
                Log.e("Dashboard", "Error, $it")
            }
    }
    fun navigateBack(v: View?) {
        super.onBackPressed();
    }
}