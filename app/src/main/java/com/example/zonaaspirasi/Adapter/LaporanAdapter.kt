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
import com.example.zonaaspirasi.Activity.DetailPengumumanActivity
import com.example.zonaaspirasi.Activity.DetailStatusActivity
import com.example.zonaaspirasi.Models.Aspirasi
import com.example.zonaaspirasi.Models.Laporan
import com.example.zonaaspirasi.R
import com.squareup.picasso.Picasso

class LaporanAdapter(var laporanData: List<Laporan>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class laporanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(laporan: Laporan) {
            val context = itemView.context
            itemView.findViewById<CardView>(R.id.item_laporan).setOnClickListener {
                val intent = Intent(context, DetailStatusActivity::class.java)
                intent.putExtra("JUDUL", laporan.judul)
                intent.putExtra("DESKRIPSI", laporan.deskripsi)
                intent.putExtra("GAMBAR", laporan.gambar)
                intent.putExtra("INSTANSI", laporan.instansi)
                intent.putExtra("KATEGORI", laporan.kategori)
                intent.putExtra("STATUS", laporan.status)
                intent.putExtra("TANGGAL", laporan.tanggal)
                intent.putExtra("LOKASI", laporan.lokasi)
                context.startActivity(intent)
            }
            itemView.findViewById<TextView>(R.id.dateField).text = laporan.tanggal
            itemView.findViewById<TextView>(R.id.titleField).text = laporan.judul
            itemView.findViewById<TextView>(R.id.descField).text = laporan.deskripsi
            val cardColor = itemView.findViewById<CardView>(R.id.statusColor);
            if(laporan.status == "Menunggu"){
                cardColor.setCardBackgroundColor(Color.parseColor("#FA1E1E"))
            }
            if(laporan.status == "Proses"){
                cardColor.setCardBackgroundColor(Color.parseColor("#FF9211"))
            }
            if(laporan.status == "Selesai"){
                cardColor.setCardBackgroundColor(Color.parseColor("#51F305"))
            }
            itemView.findViewById<TextView>(R.id.statusField).text = laporan.status
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_laporan, parent, false)
        return laporanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return laporanData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as laporanViewHolder).bind(laporanData[position])
    }

}