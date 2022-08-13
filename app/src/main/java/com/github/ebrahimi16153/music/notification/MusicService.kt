package com.github.ebrahimi16153.music.notification

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MusicService:Service() {

    private  var binder: Binder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    class MyBinder():Binder(){

        fun getService():MusicService{
            return MusicService()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


}