package com.example.musicplayer.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayer.Model.MusicResponse
import com.example.musicplayer.Model.Network

class MusicViewModel(val baseUrl: String) : ViewModel() {
    private val musicDataSet: MutableLiveData<MusicResponse> = MutableLiveData()
    private val dataErrorMessage: MutableLiveData<String> = MutableLiveData()

    fun getDataSet(): LiveData<MusicResponse> {
        return musicDataSet

    }
    fun getErrorMessage(): LiveData<String> {
        return dataErrorMessage
    }
    fun getMusic(){
        val network = Network(this)
        network.initRetrofit(baseUrl)
    }
    fun getMusicData(dataSet: MusicResponse){
        this.musicDataSet.value = dataSet

    }
    fun getErrorMessage(errorMessage: String){
        dataErrorMessage.value = errorMessage
    }
}