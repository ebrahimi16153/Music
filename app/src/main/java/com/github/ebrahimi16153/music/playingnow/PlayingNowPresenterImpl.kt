package com.github.ebrahimi16153.music.playingnow

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.github.ebrahimi16153.music.data.StaticData

class PlayingNowPresenterImpl(
    private val context: Context,
    private val view: PlayingNowContract.PlayingNowView
) : PlayingNowContract.PlayingNowPresenter {

    // play music when playingNow Activity is stared
    override fun playMusic() {
        StaticData.apply {
            if (mp.isPlaying) {

                mp.stop()
                mp.release()
                mp = MediaPlayer.create(context, Uri.parse(musicList[position].data))
                mp.start()
                view.updateMeta()
                view.updateSeekBar()
                view.setCoverMusic()
                view.showBtnPlayAnimation()
            }
        }
    }


    // button play
    override fun btnPlayMusic(context: Context) {
        StaticData.apply {

            if (!mp.isPlaying) {
                mp.start()
                view.showBtnPlayAnimation()
            } else {
                mp.pause()
                view.showBtnPlayAnimation()
            }
        }
    }

    override fun btnNextMusic() {
        StaticData.apply {

            if (position != musicCount - 1) {
                position++
            } else {
                position = 0
            }

            playMusic()

        }
    }

    override fun btnPreviousMusic() {
        StaticData.apply {

            if (position != 0) {
                position--
            } else {
                position = musicCount - 1
            }

            playMusic()

        }
    }

    override fun autoNextMusic() {
        StaticData.mp.setOnCompletionListener {
            btnNextMusic()
            autoNextMusic()
        }
    }


}