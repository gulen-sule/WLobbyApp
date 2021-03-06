package com.example.wlobbyapp.services

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import com.example.wlobbyapp.data.event.LobbyImageEvent
import com.example.wlobbyapp.data.event.LobbyImageLoadedEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import kotlin.math.absoluteValue
import kotlin.random.Random

class PosterImageService : Service() {
    private val eventBus = EventBus.getDefault()
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        eventBus.register(this)
    }

    @Subscribe
    fun loadImage(event: Any) {
        when (event) {
            is LobbyImageEvent -> {
                // val path =filesDir
                val name = Random.nextInt().absoluteValue.toString() + ".jpg"
                val file = File(filesDir, name)
                val boo = file.createNewFile()
                val url = URL(event.imageUrl)
                Log.d("imageURLTAG", filesDir.path)
                Log.d("isFileTAG", file.path)
                CoroutineScope(Dispatchers.IO).launch {
                    val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    val fileOutputStream = FileOutputStream(file)
                    Log.d("pathTAG3",file.path)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                    fileOutputStream.flush()
                    fileOutputStream.close()
                }
                eventBus.post(LobbyImageLoadedEvent(filesDir.path, name))
            }
        }
    }
}