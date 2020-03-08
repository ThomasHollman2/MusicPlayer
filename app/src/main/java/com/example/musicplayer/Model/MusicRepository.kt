package com.example.musicplayer.Model

import android.content.Context
import android.net.ConnectivityManager
import com.example.musicplayer.R
import com.example.musicplayer.View.CustomApplication
import com.example.musicplayer.ViewModel.MusicViewModel
import java.util.*

class MusicRepository(private val musicViewModel:  MusicViewModel) {

    val daoMusic = MusicDatabase.getInstance(
        CustomApplication.getCustomApp()).getDao()
    val network : Network by lazy{
        Network(musicViewModel)
    }
    //todo check for offline connection
    //todo check for cache (10 minutes)
    //todo retrieve data

    /**
     * return true if is Offline
     * Return false if is Online
     */
    fun getOfflineMode(): Boolean{

        val context: Context = CustomApplication.getCustomApp()
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo.isConnected

    }

    /**
     * Return true if a network request <10 minutes
     * Return false if network request >10 minutes
     */
    fun checkPreviousNetworkRequest() : Boolean {
        return (Calendar.getInstance().timeInMillis - getSpTime()) < (1000 * 60 * 10)

    }
    private fun saveSpTime(){
        CustomApplication.getCustomApp()
            .getSharedPreferences(CustomApplication.getCustomApp().getString(R.string.TimeNetworkTracker),
                Context.MODE_PRIVATE).edit().putLong(CustomApplication.getCustomApp()
                .getString(R.string.LAST_NETWORK_REQUEST),
                Calendar.getInstance().timeInMillis)
            .commit()


    }
    private fun getSpTime(): Long =
        CustomApplication.getCustomApp()
            .getSharedPreferences(CustomApplication.getCustomApp().getString(R.string.TimeNetworkTracker),
                Context.MODE_PRIVATE).getLong(CustomApplication.getCustomApp()
                .getString(R.string.LAST_NETWORK_REQUEST),0)

    fun getMusicData(){
        if(getOfflineMode()){
            //todo read from cache
            if(checkPreviousNetworkRequest()){
                //todo read from cache
                val listEntities = daoMusic.getDataFromCache()
                //todo parse the entities into movies poko

              //  musicViewModel.getMusicData(MusicResponse())
            }else{
                musicViewModel.getErrorMessage("No Data")
            }
        }else{
            if(checkPreviousNetworkRequest()){
                //todo read from cache
            }else{
               // network.initRetrofit()
            }
        }
    }
}