package com.example.musicplayer.View

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.Model.MusicResponse
import com.example.musicplayer.Model.Network
import com.example.musicplayer.R

class MusicAdapter : RecyclerView.Adapter<MusicViewHolder>(){
    private val TAG = Network::class.java.simpleName
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
        Log.d(TAG, "getItemCount" + dataSet?.results?.size)
        return dataSet?.results?.size ?: 0
    }
    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder" + dataSet?.results?.get(position))
        dataSet?.results?.get(position)?.let {
            holder.onBind(it)}
    }
}