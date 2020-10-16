package com.example.drawapp

import android.app.Service
import android.content.Intent
import android.content.Intent.getIntent
import android.graphics.Bitmap
import android.os.Binder
import android.os.IBinder
import android.os.Parcelable
import android.widget.Toast
import com.example.drawapp.koin.BITMAP_KEY
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class SaveService : Service() {


    private val imageSaver: ImageSaver by inject()

    inner class MyServiceBinder : Binder() {
        fun getService(): SaveService = this@SaveService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MyServiceBinder()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }


    fun saveBitmap(bitmap: Bitmap) {
        GlobalScope.launch {
            imageSaver.saveImage(bitmap)
        }
    }


}
