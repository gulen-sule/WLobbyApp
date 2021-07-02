package com.example.wlobbyapp

import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WLobbyApplication : MultiDexApplication() {// application en ust classlardan butun activitylerin buna erisimi var  observer pattern ile
    //buna erisip sonra da baska activityleri veya fragmentlari burdan es zamanli uyarabiliyorsun

    var progressValue = MutableLiveData(0)

    companion object {

        private var app: WLobbyApplication? = null

        fun getInstance(): WLobbyApplication {

            if (app == null)
                return WLobbyApplication()

            return app as WLobbyApplication
        }
    }

    override fun onCreate() {
        super.onCreate()


    }
}