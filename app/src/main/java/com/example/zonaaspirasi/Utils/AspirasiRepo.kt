package com.example.zonaaspirasi.Utils

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AspirasiRepo {
    private val firebaseFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getAspirasilist(): Task<QuerySnapshot> {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.getCurrentUser()
        val userID = firebaseUser!!.getUid()
        return firebaseFirestore.collection("aspirasi").whereEqualTo("UID", userID).get()
    }
}