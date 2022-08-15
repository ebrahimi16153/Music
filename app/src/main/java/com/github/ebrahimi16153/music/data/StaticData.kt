package com.github.ebrahimi16153.music.data

import android.media.MediaPlayer
import androidx.constraintlayout.motion.widget.MotionLayout
import com.github.ebrahimi16153.music.data.model.MusicFile

class StaticData {
    companion object{

        var mp = MediaPlayer()
        var musicList = mutableListOf<MusicFile>()
        var position = 0
        var musicCount = 0
        lateinit var motionLayoutAlbumMusic: MotionLayout
        lateinit var motionLayoutArtist: MotionLayout

        const val CAHANNEL_ID_1: String = "CHANNEL_1"
        const val CAHANNEL_ID_2: String = "CHANNEL_2"
        const val ACTION_NEXT: String = "NEXT"
        const val ACTION_PLAY: String = "PLAY"
        const val ACTION_PREV: String = "PREVIOUS"

    }
}