package com.example.zonaaspirasi.Adapter

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.zonaaspirasi.Activity.CheckStatusActivity
import com.example.zonaaspirasi.Activity.DetailPengumumanActivity
import com.example.zonaaspirasi.Models.Pengumuman
import com.example.zonaaspirasi.R
import com.squareup.picasso.Picasso

class PengumumanAdapter(var pengumumanData: List<Pengumuman>) :
    RecyclerView.Adapter<PengumumanAdapter.PengumumanViewHolder>() {
    inner class PengumumanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengumumanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pengumuman, parent, false)
        return PengumumanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PengumumanViewHolder, position: Int) {
        holder.itemView.apply {
            val pengumuman = pengumumanData.get(position)
            findViewById<CardView>(R.id.item_pengumuman).setOnClickListener {
                val intent = Intent(context, DetailPengumumanActivity::class.java)
                intent.putExtra("JUDUL", pengumuman.judul)
                intent.putExtra("TANGGAL", pengumuman.tanggal)
                context.startActivity(intent)
            }
            Picasso.get().load(pengumuman.gambar).into(findViewById<ImageView>(R.id.imagePengumuman));
            findViewById<TextView>(R.id.tanggalPengumuman).text = pengumuman.tanggal
            findViewById<TextView>(R.id.judulPengumuman).text = pengumuman.judul
            findViewById<TextView>(R.id.descPengumuman).text = pengumuman.deskripsi
        }
    }

    override fun getItemCount(): Int {
        return pengumumanData.size
    }

}