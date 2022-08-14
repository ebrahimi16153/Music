package com.github.ebrahimi16153.music.musiclist

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData

class MusicListPresenterImpl(
    private val context: Context,
    private val view: MusicListContract.MusicListView
) : MusicListContract.MusicListPresenter {


    override fun goToPlayingNow() {
//        if (StaticData.allTrack.isNotEmpty()){
//            context.startActivity(Intent(context, PlayingNow::class.java))
//        }else{
//            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
//        }
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