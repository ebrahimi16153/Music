package com.github.ebrahimi16153.music.fragment.album

import android.content.Context
import com.github.ebrahimi16153.music.data.DataInteractor
import com.github.ebrahimi16153.music.data.model.Album
import com.github.ebrahimi16153.music.fragment.alltrack.AllMusicContract

class AlbumPresenterImpl(val context: Context,val view: AlbumMusicContract.AlbumView):AlbumMusicContract.AlbumPresenter {
    override fun setList() {
        val data = DataInteractor(context)
        if (data.getAlbum().isNotEmpty()){
            val list :MutableList<Album> = mutableListOf()
            list.addAll(data.getAlbum())
            view.setList(list)
        }else{
            view.onError("Album List is Empty")
        }
    }
}