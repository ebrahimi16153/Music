package com.github.ebrahimi16153.music.fragment.artists

interface ArtistContract {

    interface ArtistView{
        fun setList(list:MutableList<String>)
        fun onError(massage:String)
    }

    interface ArtistPresenter{
        fun setList()
    }


}