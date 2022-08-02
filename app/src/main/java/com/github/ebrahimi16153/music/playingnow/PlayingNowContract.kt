package com.github.ebrahimi16153.music.playingnow

import android.content.Context


interface PlayingNowContract {

    interface PlayingNowView {

        fun showBtnPlayAnimation()
        fun updateMeta()
        fun setCoverMusic()
        fun updateSeekBar()
    }

    interface PlayingNowPresenter {

        fun playMusic()
        fun btnPlayMusic(context: Context)
        fun btnNextMusic()
        fun btnPreviousMusic()
        fun autoNextMusic()

    }


}