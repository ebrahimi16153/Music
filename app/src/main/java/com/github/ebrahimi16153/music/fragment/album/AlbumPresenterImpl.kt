package com.github.ebrahimi16153.music.fragment.album

import android.content.Context
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.TrackListInteractor

class AlbumPresenterImpl( val view:AlbumContract.AlbumView):AlbumContract.AlbumPresenter {
    override fun getList() {
         if (StaticData.albumList.isNotEmpty()){
             view.setList()
         }else{
             view.onError("Music list is Empty")
         }
    }


}