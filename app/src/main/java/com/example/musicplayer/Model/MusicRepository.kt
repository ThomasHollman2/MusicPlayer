package com.example.musicplayer.Model

import android.content.Context
import android.net.ConnectivityManager
import com.example.musicplayer.R
import com.example.musicplayer.View.CustomApplication
import com.example.musicplayer.ViewModel.MusicViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.*

class MusicRepository(private val musicViewModel:  MusicViewModel) {

    val cacheSize = (5 * 1024 * 1024).toLong()
    lateinit var myCache: Cache

    fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            CustomApplication.getCustomApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

    fun getMusic(baseUrl: String, context: Context) {
        myCache = Cache(context.cacheDir, cacheSize)
        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isOnline())
                    request.newBuilder().header(
                        "MusicAppPlayer-Data-Cache",
                        "public, max-age=" + 5
                    ).build()
                else
                    request.newBuilder().header(
                        "MusicAppPlayer-Data-Cache",
                        "public, only-if-cached, max-stale=" + 60 * 10
                    ).build()
                chain.proceed(request)
            }.build()
        val network = Network(musicViewModel)
        network.initRetrofit(baseUrl, okHttpClient)
    }
}