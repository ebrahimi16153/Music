package com.github.ebrahimi16153.music.playingnow

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.databinding.ActivityPlayingNowBinding

class PlayingNow : AppCompatActivity(), PlayingNowContract.PlayingNowView {
    //binding
    private var flag = false
    private lateinit var binding: ActivityPlayingNowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingNowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presenter = PlayingNowPresenterImpl(this, this)

        // play music
        presenter.playMusic()

        //btn play
        binding.btnPlay.setOnClickListener {
            presenter.btnPlayMusic(this)
        }

        //btn next
        binding.btnNext.setOnClickListener {
            presenter.btnNextMusic()
        }

        //btn previous
        binding.btnPrevious.setOnClickListener {
            presenter.btnPreviousMusic()
        }

        // seekbar change listener
        binding.progress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    StaticData.mp.seekTo(seekBar.progress)
                }
            }

        })

    }

    // set play and pause BtnPLay
    override fun showBtnPlayAnimation() {
        if (StaticData.mp.isPlaying) {
            binding.drawablePlay.setImageResource(R.drawable.ic_round_pause_24)
        } else {
            binding.drawablePlay.setImageResource(R.drawable.ic_round_play_arrow_24)
        }
    }

    // set title and artist textView
    override fun updateMeta() {
        StaticData.apply {
            binding.musicArtist.text = musicList[position].artist
            binding.musicTitle.text = musicList[position].title
        }
    }

    // set cover music
    override fun setCoverMusic() {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(StaticData.musicList[StaticData.position].data)
        val data = mmr.embeddedPicture

        if (data != null) {
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            binding.coverImg.setImageBitmap(bitmap)
            binding.coverImg.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            binding.coverImg.setImageResource(R.drawable.cover)
            binding.coverImg.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }

    // implement seekbar
    override fun updateSeekBar() {

        val updatedSeekbar = Thread() {
            run {
                val max = StaticData.mp.duration
                var current = StaticData.mp.currentPosition
                binding.progress.max = max

                while (current < max) {
                    try {
                        Thread.sleep(100)
                        current = StaticData.mp.currentPosition
                        binding.progress.progress = current

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }


            }
        }

        updatedSeekbar.start()
    }

}