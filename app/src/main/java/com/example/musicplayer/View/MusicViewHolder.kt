package com.example.musicplayer.View


import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer.Model.MusicPoko
import com.example.musicplayer.R

class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvArtistName: TextView = itemView.findViewById(R.id.artist_name)
    val tvTrackName: TextView = itemView.findViewById(R.id.song_name)
    val tvPrice: TextView = itemView.findViewById(R.id.price)
    val ivArtwork: ImageView = itemView.findViewById(R.id.artwork)

    fun onBind(item: MusicPoko){
        tvArtistName.text = item.artistName.toString()
        tvTrackName.text = item.collectionName.toString()
        tvPrice.text = item.trackPrice.toString()
        Glide.with(itemView).load(item.artworkUrl60).into(ivArtwork)
        itemView.setOnClickListener(
        object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent : Intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(item.previewUrl), "audio/*")
                itemView.context.startActivity(intent)
            }
        }
        )
    }
}