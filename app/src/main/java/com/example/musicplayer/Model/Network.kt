package com.example.musicplayer.Model

import android.util.Log
import com.example.musicplayer.ViewModel.MusicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Network(val viewModel: MusicViewModel) {
private val TAG = Network::class.java.simpleName

    fun initRetrofit(baseUrl: String){
        Log.d(TAG, "initRetrofit")
        getApi(baseUrl).getRock().enqueue(object  : Callback<MusicResponse> {
            override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
             //   Log.d(TAG, "onFailure")
                //viewModel.getErrorMessage(t.localizedMessage)
            }
            override fun onResponse(
                call: Call<MusicResponse>,
                response: Response<MusicResponse>
            ) {
                response.body()?.let { viewModel.getRockData(it) }
            }
        })


    getApi(baseUrl).getClassic().enqueue(object  : Callback<MusicResponse> {
        override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
            //   Log.d(TAG, "onFailure")
            //viewModel.getErrorMessage(t.localizedMessage)
        }
        override fun onResponse(
            call: Call<MusicResponse>,
            response: Response<MusicResponse>
        ) {
            response.body()?.let { viewModel.getClassicData(it) }
        }
    })


    getApi(baseUrl).getPop().enqueue(object  : Callback<MusicResponse> {
    override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
        //   Log.d(TAG, "onFailure")
        //viewModel.getErrorMessage(t.localizedMessage)
    }
    override fun onResponse(
        call: Call<MusicResponse>,
        response: Response<MusicResponse>
    ) {
        response.body()?.let { viewModel.getPopData(it) }
    }
})


}

    fun getApi(url: String): MusicApi{

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build().create(MusicApi::class.java)
    }
}