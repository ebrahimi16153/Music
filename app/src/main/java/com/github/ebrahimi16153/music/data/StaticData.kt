package com.github.ebrahimi16153.music.data

import android.media.MediaPlayer
import androidx.constraintlayout.motion.widget.MotionLayout
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.model.AlbumMusic
import com.github.ebrahimi16153.music.data.model.MusicFile
import com.github.ebrahimi16153.music.fragment.alltrack.AllTrack

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

        lateinit var motionLayoutAlbumMusic :MotionLayout
        lateinit var motionLayoutArtist :MotionLayout



    }
}