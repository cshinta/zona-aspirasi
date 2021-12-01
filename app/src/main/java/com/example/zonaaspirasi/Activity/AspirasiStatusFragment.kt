package com.example.zonaaspirasi.Activity

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zonaaspirasi.Adapter.AspirasiAdapter
import com.example.zonaaspirasi.Adapter.PengumumanAdapter
import com.example.zonaaspirasi.Models.Aspirasi
import com.example.zonaaspirasi.Models.Pengumuman
import com.example.zonaaspirasi.R
import com.example.zonaaspirasi.Utils.AspirasiRepo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AspirasiStatusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AspirasiStatusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rvAspirasi: RecyclerView
    private lateinit var  aspirasiRepo : AspirasiRepo
    private var postList : MutableList<Aspirasi> = ArrayList()
    private var aspirasiAdapter : AspirasiAdapter = AspirasiAdapter(postList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_aspirasi_status, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        aspirasiRepo = AspirasiRepo()
        loadPostData()
        rvAspirasi = view.findViewById(R.id.aspirasi_rv)
        rvAspirasi.layoutManager = LinearLayoutManager(activity)
        rvAspirasi.adapter = aspirasiAdapter
    }
    private fun loadPostData(){
        aspirasiRepo.getAspirasilist()
            .addOnCompleteListener{
                postList = it.result!!.toObjects(Aspirasi::class.java)
                if(postList.size > 0){
                    view?.findViewById<LinearLayout>(R.id.emptyLayoutAspirasi)?.setVisibility(View.GONE)
                }
                else{
                    view?.findViewById<LinearLayout>(R.id.emptyLayoutAspirasi)?.setVisibility(View.VISIBLE)
                }
                aspirasiAdapter.aspirasiData= postList
                aspirasiAdapter.notifyDataSetChanged()
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
         * @return A new instance of fragment AspirasiStatusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AspirasiStatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}