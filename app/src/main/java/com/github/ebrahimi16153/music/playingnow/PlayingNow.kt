package com.github.ebrahimi16153.music.playingnow

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.databinding.ActivityPlayingNowBinding
import com.github.ebrahimi16153.music.notification.MusicService
import com.github.ebrahimi16153.music.notification.NotificationReceiver

class PlayingNow : AppCompatActivity(), PlayingNowContract.PlayingNowView, ServiceConnection {
    //binding
    private var flag = false
    private lateinit var binding: ActivityPlayingNowBinding
    private lateinit var musicService: MusicService
    private lateinit var  mediaSession:MediaSessionCompat

    // onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingNowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val presenter = PlayingNowPresenterImpl(this, this)
        mediaSession = MediaSessionCompat(this,"Music Player")

        // play music
        val key = intent.getStringExtra("fromList") ?: "no"
        presenter.playMusic(key)


        //btn play
        binding.btnPlay.setOnClickListener {
            presenter.btnPlayMusic(this)
            showNotification(R.drawable.ic_round_play_arrow_24,StaticData.musicList[StaticData.position].data)
        }

        //btn next
        binding.btnNext.setOnClickListener {
            presenter.btnNextMusic()
            showNotification(R.drawable.ic_round_play_arrow_24,StaticData.musicList[StaticData.position].data)

        }

        //btn previous
        binding.btnPrevious.setOnClickListener {
            presenter.btnPreviousMusic()
            showNotification(R.drawable.ic_round_play_arrow_24,StaticData.musicList[StaticData.position].data)

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


    fun  showNotification(playPauseButton:Int,path:String){






//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.q){
//
//
//
//
//
//        }


        val intent = Intent(this,PlayingNow::class.java)
        val contentIntent = PendingIntent.getActivities(this,0, arrayOf(intent),PendingIntent.FLAG_IMMUTABLE)



        val prevIntent = Intent(this,NotificationReceiver::class.java).setAction(StaticData.ACTION_PREV)
        val prevPendingIntent = PendingIntent.getBroadcast(this,0,prevIntent, PendingIntent.FLAG_IMMUTABLE)


        val playIntent = Intent(this,NotificationReceiver::class.java).setAction(StaticData.ACTION_PLAY)
        val playPendingIntent = PendingIntent.getBroadcast(this,0,playIntent, PendingIntent.FLAG_IMMUTABLE )

        val nextIntent = Intent(this,NotificationReceiver::class.java).setAction(StaticData.ACTION_NEXT)
        val nextPendingIntent = PendingIntent.getBroadcast(this,0,nextIntent, PendingIntent.FLAG_IMMUTABLE)



        val notification = NotificationCompat.Builder(this,StaticData.CAHANNEL_ID_1)
            .setSmallIcon(R.mipmap.adaptive_icon_foreground)
            .setLargeIcon(getCover(path))
            .setContentTitle(binding.musicTitle.text)
            .setContentText(binding.musicArtist.text)
            .addAction(R.drawable.ic_round_fast_rewind_24,"Previous",prevPendingIntent)
            .addAction(playPauseButton,"play",playPendingIntent)
            .addAction(R.drawable.ic_round_fast_forward_24,"Next",nextPendingIntent)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(contentIntent)
            .setSilent(true)
            .build()

        val notificationManager:NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0,notification)




        getCover(path)
    }

    private fun getCover(path: String):Bitmap {
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