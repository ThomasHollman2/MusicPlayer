package com.example.musicplayer.Model

import android.util.Log
import com.example.musicplayer.ViewModel.MusicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network(val viewModel: MusicViewModel) {


    fun initRetrofit(baseUrl: String){
        getApi(baseUrl).getRock().enqueue(object  : Callback<MusicResponse> {
            override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                viewModel.getErrorMessage(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MusicResponse>,
                response: Response<MusicResponse>

            ) {
                response.body()?.let { viewModel.getMusicData(it) }
            }
        })

    }

    fun getApi(url: String): MusicApi{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build().create(MusicApi::class.java)
    }
}