package com.github.ebrahimi16153.music.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.github.ebrahimi16153.music.data.StaticData

class Notification :Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

           val notificationChannel1 = NotificationChannel(StaticData.CAHANNEL_ID_1,
           "Music Player Notification",NotificationManager.IMPORTANCE_HIGH)
           notificationChannel1.description = "Music Control"

//           val notificationChannel2 = NotificationChannel(StaticData.CAHANNEL_ID_2,
//           "Channel(2)",NotificationManager.IMPORTANCE_HIGH)
//           notificationChannel2.description = "Channel 2 Description"

           val notificationManager = getSystemService(NotificationManager::class.java)

           notificationManager.createNotificationChannel(notificationChannel1)
//           notificationManager.createNotificationChannel(notificationChannel2)


       }


    }

}