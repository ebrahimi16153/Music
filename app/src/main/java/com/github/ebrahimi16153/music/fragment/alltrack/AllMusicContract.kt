package com.github.ebrahimi16153.music.fragment.alltrack

import com.github.ebrahimi16153.music.data.model.MusicFile

interface AllMusicContract {
    interface AllMusicView{
       fun onError(massage:String)
       fun setList(list: MutableList<MusicFile>)
    }
    interface AllMusicPresenter{
        fun setList()
    }

}