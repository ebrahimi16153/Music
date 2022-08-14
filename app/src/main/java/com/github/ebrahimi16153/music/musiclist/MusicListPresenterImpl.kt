package com.github.ebrahimi16153.music.musiclist

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import android.widget.Toast
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.DataInteractor
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.playingnow.PlayingNow

class MusicListPresenterImpl(private val context: Context, private val view: MusicListContract.MusicListView
) : MusicListContract.MusicListPresenter {


    override fun goToPlayingNow() {
           val data= DataInteractor(context)
        if (data.getListOfMusic().isNotEmpty() && StaticData.musicList.isEmpty()){
            StaticData.musicList.clear()
            StaticData.musicList.addAll(data.getListOfMusic())
            StaticData.musicCount = data.getListOfMusic().size
            context.startActivity(Intent(context, PlayingNow::class.java))
        }else if(StaticData.musicList.isNotEmpty()){
            context.startActivity(Intent(context, PlayingNow::class.java))
        }else{
            Toast.makeText(context,"PlayList is Empty", Toast.LENGTH_SHORT).show()
        }

    }

    override fun setTabs() {
        view.setTabs()


    }

    override fun updateMetaData(musicCover: ImageView) {
        if (StaticData.musicList.isNotEmpty()) {
            // set coverMusic
            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(StaticData.musicList[StaticData.position].data)
            val data = mmr.embeddedPicture

            if (data != null) {
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                musicCover.setImageBitmap(bitmap)
                musicCover.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                musicCover.setImageResource(R.drawable.cover)
                musicCover.scaleType = ImageView.ScaleType.FIT_CENTER

            }

            //set title
        }

    }



}