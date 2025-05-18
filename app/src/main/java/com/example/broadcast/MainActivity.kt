package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1?.action == "loaded") {
                val percent = p1.getIntExtra("percent", 0 )
                progressBar.progress = percent
            }
        }
    }
    private var countOfClicks = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(MyReceiver.ACTION_CLICKED).putExtra(MyReceiver.COUNT_OF_CLICKS, countOfClicks++)
            sendBroadcast(intent)
        }

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction("loaded")
        }

        registerReceiver(receiver, intentFilter)

        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(receiver)
    }
}