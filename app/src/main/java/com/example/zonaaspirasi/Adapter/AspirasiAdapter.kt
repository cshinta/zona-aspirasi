package com.example.zonaaspirasi.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.zonaaspirasi.Activity.DetailAspirasiActivity
import com.example.zonaaspirasi.Activity.DetailPengumumanActivity
import com.example.zonaaspirasi.Activity.DetailStatusActivity
import com.example.zonaaspirasi.Models.Aspirasi
import com.example.zonaaspirasi.Models.Laporan
import com.example.zonaaspirasi.Models.PengumumanCoba
import com.example.zonaaspirasi.R
import com.squareup.picasso.Picasso

class AspirasiAdapter(var aspirasiData: List<Aspirasi>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class aspirasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(aspirasi: Aspirasi) {
            val context = itemView.context
            itemView.findViewById<CardView>(R.id.item_aspirasi).setOnClickListener {
                val intent = Intent(context, DetailAspirasiActivity::class.java)
                intent.putExtra("JUDUL", aspirasi.judul)
                intent.putExtra("DESKRIPSI", aspirasi.deskripsi)
                intent.putExtra("GAMBAR", aspirasi.gambar)
                intent.putExtra("INSTANSI", aspirasi.instansi)
                intent.putExtra("KATEGORI", aspirasi.kategori)
                context.startActivity(intent)
            }
            itemView.findViewById<TextView>(R.id.titleField).text = aspirasi.judul
            itemView.findViewById<TextView>(R.id.descField).text = aspirasi.deskripsi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_aspirasi, parent, false)
        return aspirasiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aspirasiData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as aspirasiViewHolder).bind(aspirasiData[position])
    }

}