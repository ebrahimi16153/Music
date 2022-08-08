package com.github.ebrahimi16153.music.fragment.album

interface AlbumContract {
    interface AlbumView {
        fun setList()
        fun onError(massage:String)
    }

    interface AlbumPresenter {
        fun getList()

    }

}