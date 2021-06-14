package com.example.wlobbyapp

import android.app.Application
import androidx.lifecycle.MutableLiveData

class LobbyApp : Application() {// application en ust classlardan butun activitylerin buna erisimi var  observer pattern ile
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
}