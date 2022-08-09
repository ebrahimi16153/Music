package com.github.ebrahimi16153.music.fragment.album

import android.content.Context
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.TrackListInteractor

class AlbumPresenterImpl(val context: Context , val view:AlbumContract.AlbumView):AlbumContract.AlbumPresenter {
    override fun getList() {
        val data = TrackListInteractor(context)
         if (data.getAlbum().isNotEmpty()){
             StaticData.albumList = data.getAlbum()
             view.setList()
         }else{
             view.onError("Music list is Empty")
         }
    }


}