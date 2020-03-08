package com.example.musicplayer.Model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "music_table")
class MusicEntitie(
    @ColumnInfo(name= "artistName")
    val artistName: String,
    @ColumnInfo(name = "collectionName")
    val collectionName: String,
    @ColumnInfo(name = "artworkUrl")
    val artworkUrl60: String,
    @ColumnInfo(name = "trackPrice")
    val trackPrice: Float,
    @ColumnInfo(name = "previewUrl")
    val previewUrl: String
) {


}