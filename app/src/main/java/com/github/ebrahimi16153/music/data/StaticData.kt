package com.github.ebrahimi16153.music.data

import android.media.MediaPlayer
import androidx.constraintlayout.motion.widget.MotionLayout
import com.github.ebrahimi16153.music.data.model.AlbumMusic
import com.github.ebrahimi16153.music.data.model.MusicFile

class StaticData {
    companion object{

        var mp = MediaPlayer()
        var musicList = mutableListOf<MusicFile>()
        var position = 0
        var musicCount = 0
        var allTrack = mutableListOf<MusicFile>()
        var albumList = mutableListOf<AlbumMusic>()
        var currentAlbum = mutableListOf<MusicFile>()
        var artistList = mutableListOf<String>()
        val currentArtist = mutableListOf<MusicFile>()

        lateinit var motionLayoutAlbumMusic: MotionLayout
        lateinit var motionLayoutArtist: MotionLayout

        val CAHANNEL_ID_1: String = "CHANNEL_1"
        val CAHANNEL_ID_2: String = "CHANNEL_2"
        val ACTION_NEXT: String = "NEXT"
        val ACTION_PLAY: String = "PLAY"
        val ACTION_PREV: String = "PREVIOUS"

    }
}