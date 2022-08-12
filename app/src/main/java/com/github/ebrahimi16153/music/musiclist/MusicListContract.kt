package com.github.ebrahimi16153.music.musiclist

import android.content.Context
import android.widget.ImageView
import android.widget.TextView

interface MusicListContract {

    interface MusicListView{


        fun setTabs()
    }


    interface MusicListPresenter{

        fun goToPlayingNow()
        fun setTabs()
        fun updateMetaData(musicCover:ImageView)
    }


}