package com.github.ebrahimi16153.music.playingnow

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.databinding.ActivityPlayingNowBinding
import com.github.ebrahimi16153.music.musiclist.MusicList
import com.github.ebrahimi16153.music.notification.NotificationReceiver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector

class PlayingNow : AppCompatActivity(), PlayingNowContract.PlayingNowView {
    //binding
    private lateinit var binding: ActivityPlayingNowBinding
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var presenter: PlayingNowContract.PlayingNowPresenter
    private lateinit var notificationCover: Bitmap
    private lateinit var notificationManager: NotificationManager


    // onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingNowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (StaticData.musicList.size != 0){
            presenter = PlayingNowPresenterImpl(this, this)
            StaticData.presenter = presenter
            mediaSession = MediaSessionCompat(this, "Music")

            // play music
            val key = intent.getStringExtra("fromList") ?: "no"
            presenter.playMusic(key)


            //btn play
            binding.btnPlay.setOnClickListener {
                presenter.btnPlayMusic()

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

            // btn back

            binding.btnBack.setOnClickListener {
                presenter.back()
            }
        }else{
            startActivity(Intent(this,MusicList::class.java))
            finish()
        }

    }


    override fun onResume() {
        showBtnPlayAnimation()
        super.onResume()
    }

    // set play and pause BtnPLay
    override fun showBtnPlayAnimation() {
        if (StaticData.mp.isPlaying) {
            binding.drawablePlay.setImageResource(R.drawable.ic_round_pause_24)
            showNotification(
                R.drawable.ic_round_pause_24,
                StaticData.musicList[StaticData.position].data
            )

        } else {
            binding.drawablePlay.setImageResource(R.drawable.ic_round_play_arrow_24)
            showNotification(
                R.drawable.ic_round_play_arrow_24,
                StaticData.musicList[StaticData.position].data
            )

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
            notificationCover = bitmap
            binding.coverImg.setImageBitmap(bitmap)
            binding.coverImg.scaleType = ImageView.ScaleType.CENTER_CROP

        } else {
            notificationCover = BitmapFactory.decodeResource(resources, R.drawable.cover)
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

    override fun back() {
        super.onBackPressed()
    }


    private fun showNotification(playPauseButton: Int, path: String) {


         val intent = Intent(this,PlayingNow::class.java)


        val contentIntent =
            PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_IMMUTABLE)

        val prevIntent =
            Intent(this, NotificationReceiver::class.java).setAction(StaticData.ACTION_PREV)
        val prevPendingIntent =
            PendingIntent.getBroadcast(this, 0, prevIntent, PendingIntent.FLAG_IMMUTABLE)


        val playIntent =
            Intent(this, NotificationReceiver::class.java).setAction(StaticData.ACTION_PLAY)
        val playPendingIntent =
            PendingIntent.getBroadcast(this, 0, playIntent, PendingIntent.FLAG_IMMUTABLE)

        val nextIntent =
            Intent(this, NotificationReceiver::class.java).setAction(StaticData.ACTION_NEXT)
        val nextPendingIntent =
            PendingIntent.getBroadcast(this, 0, nextIntent, PendingIntent.FLAG_IMMUTABLE)


        val notification = NotificationCompat.Builder(this, StaticData.CAHANNEL_ID_1)
            .setSmallIcon(R.mipmap.adaptive_icon_foreground)
            .setLargeIcon(getCover(path))
            .setContentTitle(binding.musicTitle.text)
            .setContentText(binding.musicArtist.text)
            .addAction(R.drawable.ic_round_fast_rewind_24, "Previous", prevPendingIntent)
            .addAction(playPauseButton, "play", playPendingIntent)
            .addAction(R.drawable.ic_round_fast_forward_24, "Next", nextPendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(1)
            )
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(contentIntent)
            .setSilent(true)
            .setShowWhen(false)
            .build()

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)


    }

    private fun getCover(path: String): Bitmap {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(path)
        val data = mmr.embeddedPicture
        return if (data != null) {
            BitmapFactory.decodeByteArray(data, 0, data.size)
        } else {

            BitmapFactory.decodeResource(resources, R.drawable.cover)
        }
    }


}