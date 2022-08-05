package com.github.ebrahimi16153.music.fragment.alltrack

import android.content.Context
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.TrackListInteractor

class TrackPresenterImpl(private val contract:Context,private val view: TrackContract.TrackView):TrackContract.TrackPresenter {
    override fun getList() {
        val getData = TrackListInteractor(contract)
        if (getData.getListOfMusic().isNotEmpty() ){
            StaticData.musicList = getData.getListOfMusic()
            view.setList()
        }else{
            view.onError("Music list is Empty")
        }
    }

}