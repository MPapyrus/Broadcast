package com.example.broadcast

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlin.concurrent.thread

class MyService: Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        thread {
            for (i in 1..10) {
                Thread.sleep(1000)
                val progressIntent = Intent("loaded").putExtra("percent", i*10)
                sendBroadcast(progressIntent)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}