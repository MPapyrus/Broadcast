package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        when (p1?.action) {
            "loaded" -> {
                val progress = p1.getIntExtra("percent", 0)
                Toast.makeText(p0, "Progress: $progress", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(p0, "Battery Low", Toast.LENGTH_SHORT).show()
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnedOn = p1.getBooleanExtra("state", false)

                Toast.makeText(p0, "Airplane mode chandeg. TurnedOn: $turnedOn", Toast.LENGTH_SHORT).show()
            }

            ACTION_CLICKED -> {
                val countOfClicks = p1.getIntExtra(COUNT_OF_CLICKS, 0)
                Toast.makeText(p0, "$ACTION_CLICKED: $countOfClicks", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        const val ACTION_CLICKED = "clicked"
        const val COUNT_OF_CLICKS = "clicks"
    }
}