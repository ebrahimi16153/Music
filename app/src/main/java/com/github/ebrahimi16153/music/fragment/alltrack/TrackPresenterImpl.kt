package com.github.ebrahimi16153.music.fragment.alltrack

import android.content.Context
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.TrackListInteractor

class TrackPresenterImpl(private val view: TrackContract.TrackView):TrackContract.TrackPresenter {
    override fun getList() {
        if (StaticData.allTrack.isNotEmpty() ){
            view.setList()
        }else{
            view.onError("Music list is Empty")
        }
    }

}