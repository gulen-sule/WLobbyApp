package com.example.wlobbyapp

import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LobbyApp : MultiDexApplication() {// application en ust classlardan butun activitylerin buna erisimi var  observer pattern ile
    //buna erisip sonra da baska activityleri veya fragmentlari burdan es zamanli uyarabiliyorsun

    var progressValue = MutableLiveData(0)

    companion object {

        private var app: LobbyApp? = null

        fun getInstance(): LobbyApp {

            if (app == null)
                return LobbyApp()

            return app as LobbyApp
        }
    }

    override fun onCreate() {
        super.onCreate()


    }
}