package com.example.musicplayer.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoMusic {
    @Insert
    fun saveDataIntoCache(data: List<MusicEntitie>)

    @Query("SELECT * FROM music_table")
    fun getRockMusic(): MusicResponse

    @Query("SELECT * FROM music_table")
    fun getClassicMusic(): MusicResponse

    @Query("SELECT * FROM music_table")
    fun getPopMusic(): MusicResponse




}