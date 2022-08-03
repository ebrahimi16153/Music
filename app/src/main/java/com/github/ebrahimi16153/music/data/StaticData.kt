package com.github.ebrahimi16153.music.data

import android.media.MediaPlayer
import com.github.ebrahimi16153.music.data.MusicFile

class StaticData {
    companion object{
        var mp = MediaPlayer()
        var musicList = mutableListOf<MusicFile>()
        var position = 0
        var musicCount = 0
        var playingNow = false


    }
}