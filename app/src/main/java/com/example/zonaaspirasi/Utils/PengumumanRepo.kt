package com.example.zonaaspirasi.Utils

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class PengumumanRepo {
    private val firebaseFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getPengumumanlist(): Task<QuerySnapshot> {
        return firebaseFirestore.collection("pengumuman")
            .get()
    }
}