package com.github.ebrahimi16153.music.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.github.ebrahimi16153.music.data.StaticData

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent != null) {

            when (intent.action) {

                StaticData.ACTION_PREV -> {
                    Toast.makeText(context, "PREVIOUS", Toast.LENGTH_SHORT).show()
                }
                StaticData.ACTION_PLAY -> {
                    Toast.makeText(context, "PLAY", Toast.LENGTH_SHORT).show()
                }
                StaticData.ACTION_NEXT -> {
                    Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}