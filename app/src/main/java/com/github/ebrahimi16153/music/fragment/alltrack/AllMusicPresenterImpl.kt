package com.github.ebrahimi16153.music.fragment.alltrack

import android.content.Context
import com.github.ebrahimi16153.music.data.DataInteractor
import com.github.ebrahimi16153.music.data.model.MusicFile

class AllMusicPresenterImpl(val context: Context,val view:AllMusicContract.AllMusicView):AllMusicContract.AllMusicPresenter {
    override fun setList() {
        val data = DataInteractor(context)
        if (data.getListOfMusic().isNotEmpty()){

            val list = mutableListOf<MusicFile>()
            list.addAll(data.getListOfMusic())
            view.setList(list)
        }else{
            view.onError("List Music is Empty")
        }

    }
}