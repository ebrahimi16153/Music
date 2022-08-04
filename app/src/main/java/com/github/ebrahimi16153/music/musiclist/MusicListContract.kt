package com.github.ebrahimi16153.music.musiclist

import android.content.Context

interface MusicListContract {

    interface MusicListView{


        fun showList()
        fun showError(string: String)
        fun setCoverMusic()
        fun updateMeta()
    }


    interface MusicListPresenter{

        fun setList()
        fun setError(string: String)
        fun goToPlayingNow()

    }


}