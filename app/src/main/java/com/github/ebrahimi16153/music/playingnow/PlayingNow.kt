package com.github.ebrahimi16153.music.playingnow

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.BitmapFactory
import android.graphics.Insets
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.databinding.ActivityPlayingNowBinding
import com.github.ebrahimi16153.music.notification.MusicService

class PlayingNow : AppCompatActivity(), PlayingNowContract.PlayingNowView, ServiceConnection {
    //binding
    private var flag = false
    private lateinit var binding: ActivityPlayingNowBinding
    private lateinit var musicService: MusicService

    // onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingNowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val presenter = PlayingNowPresenterImpl(this, this)

        // play music
        val key = intent.getStringExtra("fromList") ?: "no"
        presenter.playMusic(key)

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


        //notification service

        val intent = Intent(this,MusicService::class.java)
        bindService(intent,this, BIND_AUTO_CREATE)

    }


    override fun onPause() {
        super.onPause()
        unbindService(this)
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this,MusicService::class.java)
        bindService(intent,this, BIND_AUTO_CREATE)
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
                        Thread.sleep(500)
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

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder: MusicService.MyBinder = service as MusicService.MyBinder
        musicService = binder.getService()
        Log.e("connected", "$musicService")
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.e("DisConnected", "$musicService")

    }

}