package com.github.ebrahimi16153.music.playingnow

import android.content.Context


interface PlayingNowContract {

    interface PlayingNowView {

        fun showBtnPlayAnimation()
        fun updateMeta()
        fun setCoverMusic()
        fun updateSeekBar()
        fun back()
    }

    interface PlayingNowPresenter {

        fun playMusic(fromList:String)
        fun btnPlayMusic()
        fun btnNextMusic()
        fun btnPreviousMusic()
        fun autoNextMusic()
        fun back()

    }


}