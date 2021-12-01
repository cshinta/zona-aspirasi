package com.example.zonaaspirasi.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.zonaaspirasi.Activity.DetailPengumumanActivity
import com.example.zonaaspirasi.Models.PengumumanCoba
import com.example.zonaaspirasi.R
import com.squareup.picasso.Picasso

class PengumumanAdapter2(var postListItems: List<PengumumanCoba>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class postViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(postModel: PengumumanCoba) {
            val context = itemView.context
            itemView.findViewById<CardView>(R.id.item_pengumuman).setOnClickListener {
                val intent = Intent(context, DetailPengumumanActivity::class.java)
                intent.putExtra("JUDUL", postModel.judul)
                intent.putExtra("DESKRIPSI", postModel.deskripsi)
                intent.putExtra("TANGGAL", postModel.tanggal)
                intent.putExtra("GAMBAR", postModel.gambar)
                context.startActivity(intent)
            }
            if(postModel.gambar != ""){
                Picasso.get().load(postModel.gambar).into(itemView.findViewById<ImageView>(R.id.imagePengumuman));
            }
            itemView.findViewById<TextView>(R.id.tanggalPengumuman).text = postModel.tanggal
            itemView.findViewById<TextView>(R.id.judulPengumuman).text = postModel.judul
            if(postModel.deskripsi!!.length < 80){
                itemView.findViewById<TextView>(R.id.descPengumuman).text = postModel.deskripsi
            }
            else{
                itemView.findViewById<TextView>(R.id.descPengumuman).text = postModel.deskripsi.substring(0,80)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pengumuman, parent, false)
        return postViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postListItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as postViewHolder).bind(postListItems[position])
    }

}