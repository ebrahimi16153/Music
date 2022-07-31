package com.github.ebrahimi16153.music.musiclist

import android.content.Context
import android.widget.Toast
import com.github.ebrahimi16153.music.data.MusicListInteractor
import com.github.ebrahimi16153.music.data.StaticData

class MusicListPresenterImpl(private val context: Context, private val view:MusicListContract.MusicListView):MusicListContract.MusicListPresenter {
    override fun setList() {

        val musicData = MusicListInteractor(context)
        if (musicData.getListOfMusic().isNotEmpty()){
            StaticData.musicList = musicData.getListOfMusic()
        }
        view.showList()
    }

    override fun setError(string: String) {
      view.showError(string)
    }

}