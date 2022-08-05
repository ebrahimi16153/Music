package com.github.ebrahimi16153.music.fragment.alltrack

interface TrackContract {

    interface TrackView{
        fun setList()
        fun onError(massage:String)

    }
    interface TrackPresenter{
        fun getList()

    }
}