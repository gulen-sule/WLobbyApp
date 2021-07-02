package com.example.wlobbyapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.example.wlobbyapp.data.event.UploadServiceEvent
import org.greenrobot.eventbus.EventBus

class UploadService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("serviceTAG", "start")

       /* val eventbus = EventBus.getDefault()
        (0..10000000).forEach {
            CoroutineScope(Dispatchers.Main).launch {
                eventbus.post(UploadServiceEvent(value = it))
            }
        }*/


       // loopTest()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    lateinit var runnable: Runnable
    private var value = 0

    fun loopTest() {
        val handler = Handler()

        runnable = Runnable {
            EventBus.getDefault().post(UploadServiceEvent(value = value++))
            handler.postDelayed(runnable, 100)
        }

        handler.postDelayed(runnable, 100)
    }

}