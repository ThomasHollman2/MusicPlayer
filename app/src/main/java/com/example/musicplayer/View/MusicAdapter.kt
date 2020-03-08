package com.example.musicplayer.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.Model.MusicResponse
import com.example.musicplayer.R

class MusicAdapter : RecyclerView.Adapter<MusicViewHolder>(){

    var dataSet: MusicResponse? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.music_layout,
                parent,
                false))

    override fun getItemCount(): Int {
        return dataSet?.data?.size ?: 0
    }
    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {

        dataSet?.data?.get(position)?.let {
            holder.onBind(it)}
    }
}