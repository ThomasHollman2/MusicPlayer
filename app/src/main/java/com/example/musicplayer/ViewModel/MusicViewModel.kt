package com.example.musicplayer.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayer.Model.MusicRepository
import com.example.musicplayer.Model.MusicResponse
import com.example.musicplayer.Model.Network

class MusicViewModel(val baseUrl: String) : ViewModel() {
    private val rockDataSet: MutableLiveData<MusicResponse> = MutableLiveData()
    private val classicDataSet: MutableLiveData<MusicResponse> = MutableLiveData()
    private val popDataSet: MutableLiveData<MusicResponse> = MutableLiveData()
    private val dataErrorMessage: MutableLiveData<String> = MutableLiveData()
    private val TAG = Network::class.java.simpleName

    fun getRockDataSet(): LiveData<MusicResponse>{
        return rockDataSet
    }
    fun getPopDataSet(): LiveData<MusicResponse>{
        return popDataSet
    }
    fun getClassicDataSet(): LiveData<MusicResponse>{
        return classicDataSet
    }
    fun getErrorMessage(): LiveData<String> {
        return dataErrorMessage
    }
    fun getMusic(context: Context){
        val repo = MusicRepository(this)
        repo.getMusic(baseUrl,context)
    }
    fun getRockData(dataSet: MusicResponse){

        this.rockDataSet.value = dataSet

    }
    fun getPopData(dataSet: MusicResponse){

        this.popDataSet.value = dataSet

    }
    fun getClassicData(dataSet: MusicResponse){

        this.classicDataSet.value = dataSet

    }

    fun getErrorMessage(errorMessage: String){
        dataErrorMessage.value = errorMessage
    }
}