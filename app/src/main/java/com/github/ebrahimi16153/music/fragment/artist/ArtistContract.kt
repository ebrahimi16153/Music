package com.github.ebrahimi16153.music.fragment.artist

interface ArtistContract {

    interface ArtistView {
        fun setList()
        fun onError(massage:String)
    }

    interface ArtistPresenter {
        fun getList()

    }

}