package com.example.musicplayer.Model



data class MusicResponse(var resultCount: Int = 0, var data: List<MusicPoko>? = null)

data class MusicPoko(

    var artistName: String = "yes",
    var collectionName: String = "yes",
    var artworkUrl60: String = "yes",
    var trackPrice: Float = 1.0f,
    var previewUrl: String = "yes"
  )