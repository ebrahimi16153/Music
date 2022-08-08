package com.github.ebrahimi16153.music.data

import android.media.MediaPlayer
import androidx.constraintlayout.motion.widget.MotionLayout
import com.github.ebrahimi16153.music.data.model.MusicFile
import com.github.ebrahimi16153.music.helper.CustomScrollListener

class StaticData {
    companion object{

        var mp = MediaPlayer()
        var musicList = mutableListOf<MusicFile>()
        var position = 0
        var musicCount = 0
        var albumList = mutableListOf<String>()
        lateinit var motionLayout: MotionLayout
        val currentAlbum = mutableListOf<MusicFile>()
        lateinit var motionLayoutAlbum:MotionLayout
        val recyclerAnim = CustomScrollListener()


    }
}