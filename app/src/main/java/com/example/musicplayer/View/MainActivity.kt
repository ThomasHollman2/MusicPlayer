package com.example.musicplayer.View

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
    var selectedTab: String = "Rock"

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


        getRock(viewModel)

        tab_layout_genres.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0!!.position) {
                    0 -> {
                        getRock(viewModel!!)
                        selectedTab = "Rock"
                    }
                    1 -> {
                        getClassic(viewModel!!)
                        selectedTab = "Classic"
                    }
                    2 -> {
                        getPop(viewModel!!)
                        selectedTab = "Pop"
                    }
                    else -> {
                    }
                }
            }
        })
        swipeRefresh.setOnRefreshListener {

            when (selectedTab) {
                "Rock" -> {getRock(viewModel!!)
                swipeRefresh.isRefreshing = false}
                "Classic" -> {getClassic(viewModel!!)
                    swipeRefresh.isRefreshing = false}
                "Pop" -> {getPop(viewModel!!)
                    swipeRefresh.isRefreshing = false}

                else -> {
                }
            }

        }

        viewModel.getMusic(this)
    }

    private fun getRock(viewModel: MusicViewModel) {
        viewModel.getRockDataSet().observe(this,
            object : Observer<MusicResponse> {
                override fun onChanged(t: MusicResponse?) {
                    musicAdapter.dataSet = t
                }


            })
    }

    private fun getClassic(viewModel: MusicViewModel) {
        viewModel.getClassicDataSet().observe(this,
            object : Observer<MusicResponse> {
                override fun onChanged(t: MusicResponse?) {
                    musicAdapter.dataSet = t
                }


            })
    }

    private fun getPop(viewModel: MusicViewModel) {
        viewModel.getPopDataSet().observe(this,
            object : Observer<MusicResponse> {
                override fun onChanged(t: MusicResponse?) {
                    musicAdapter.dataSet = t
                }


            })
    }
}
