package com.example.zonaaspirasi.Activity

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zonaaspirasi.Adapter.AspirasiAdapter
import com.example.zonaaspirasi.Adapter.LaporanAdapter
import com.example.zonaaspirasi.Models.Aspirasi
import com.example.zonaaspirasi.Models.Laporan
import com.example.zonaaspirasi.R
import com.example.zonaaspirasi.Utils.LaporanRepo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LaporanStatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LaporanStatusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rvLaporan: RecyclerView
    private lateinit var  laporanRepo : LaporanRepo
    private var laporanList : MutableList<Laporan> = ArrayList()
    private var laporanAdapter : LaporanAdapter = LaporanAdapter(laporanList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_laporan_status, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        laporanRepo = LaporanRepo()
        loadPostData()
        rvLaporan = view.findViewById(R.id.laporan_rv)
        rvLaporan.layoutManager = LinearLayoutManager(activity)
        rvLaporan.adapter = laporanAdapter
    }
    private fun loadPostData(){
        laporanRepo.getLaporanlist()
            .addOnCompleteListener{
                laporanList = it.result!!.toObjects(Laporan::class.java)
                laporanAdapter.laporanData= laporanList
                Log.i("Feed", laporanList.get(0).toString())
                laporanAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener{
                Log.e("Dashboard", "Error, $it")
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LaporanStatusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LaporanStatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}