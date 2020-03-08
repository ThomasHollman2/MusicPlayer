package com.example.musicplayer.Model

import retrofit2.Call
import retrofit2.http.GET

interface MusicApi {

    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getRock(): Call<MusicResponse>
    @GET("search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    fun getClassic(): Call<MusicResponse>
    @GET("search?term=pop&amp;media=music&amp;entity=song&amp;limit=50")
    fun getPop(): Call<MusicResponse>


}