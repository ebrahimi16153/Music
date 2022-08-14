package com.github.ebrahimi16153.music.fragment.artists

import android.content.Context
import com.github.ebrahimi16153.music.data.DataInteractor

class ArtistPresenterImpl(val context: Context , val view:ArtistContract.ArtistView):ArtistContract.ArtistPresenter {
    override fun setList() {

        val data = DataInteractor(context)


        if (data.getArtist().isNotEmpty()){
            val list:MutableList<String> = mutableListOf()
            list.addAll(data.getArtist())
            view.setList(list)

        }else{
            view.onError("Artist List is Empty")
        }



    }
}