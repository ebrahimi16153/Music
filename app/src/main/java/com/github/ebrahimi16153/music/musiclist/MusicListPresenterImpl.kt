package com.github.ebrahimi16153.music.musiclist

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.FmPagerAdapter
import com.github.ebrahimi16153.music.fragment.album.AlbumMusic
import com.github.ebrahimi16153.music.fragment.alltrack.AllTrack
import com.github.ebrahimi16153.music.fragment.artist.ArtistMusic
import com.github.ebrahimi16153.music.playingnow.PlayingNow
import com.google.android.material.tabs.TabLayoutMediator

class MusicListPresenterImpl(
    private val context: Context,
    private val view: MusicListContract.MusicListView
) : MusicListContract.MusicListPresenter {


    override fun goToPlayingNow() {
        if (StaticData.musicList.isNotEmpty()){
            context.startActivity(Intent(context, PlayingNow::class.java))
        }else{
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        }
    }

    override fun setTabs() {
        view.setTabs()


    }

    override fun updateMetaData(musicCover: ImageView, title: TextView) {
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
            title.text = StaticData.musicList[StaticData.position].title

        }

    }



}