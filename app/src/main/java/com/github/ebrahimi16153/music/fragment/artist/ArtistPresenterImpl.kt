package com.github.ebrahimi16153.music.fragment.artist

import com.github.ebrahimi16153.music.data.StaticData

class ArtistPresenterImpl(val view:ArtistContract.ArtistView):ArtistContract.ArtistPresenter {
    override fun getList() {
        if (StaticData.albumList.isEmpty()){
            view.setList()
        }else{
            view.onError("Music list is Empty")
        }
    }
}