package com.example.musicplayer.View

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.Model.MusicResponse
import com.example.musicplayer.R
import com.example.musicplayer.ViewModel.MusicViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    val musicAdapter: MusicAdapter by lazy { MusicAdapter() }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager =
            LinearLayoutManager(this)
        recycler_view.adapter = musicAdapter


        val viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MusicViewModel("https://itunes.apple.com/")
                            as T
                }
            }
        ).get(MusicViewModel::class.java)


        viewModel.getDataSet().observe(this,
            Observer<MusicResponse> { t -> musicAdapter.dataSet = t })
        viewModel.getMusic()

    }


}
