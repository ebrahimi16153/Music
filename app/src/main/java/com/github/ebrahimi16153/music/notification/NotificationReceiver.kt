package com.github.ebrahimi16153.music.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.github.ebrahimi16153.music.data.StaticData

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {

            when (intent.action) {

                StaticData.ACTION_PREV -> {
                    StaticData.presenter.btnPreviousMusic()
                }
                StaticData.ACTION_PLAY -> {
                    StaticData.presenter.btnPlayMusic()
                }
                StaticData.ACTION_NEXT -> {
                    StaticData.presenter.btnNextMusic()
                }
            }

        }

    }
}