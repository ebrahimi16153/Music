package com.github.ebrahimi16153.music.fragment.album

import com.github.ebrahimi16153.music.data.model.Album

interface AlbumMusicContract {

    interface AlbumView{
        fun setList(list: MutableList<Album>)
        fun onError(massage:String)
    }


    interface AlbumPresenter{
        fun setList()
    }


}