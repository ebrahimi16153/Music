package com.github.ebrahimi16153.music.musiclist

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.MusicListAdapter
import com.github.ebrahimi16153.music.databinding.ActivityMusicListBinding

class MusicList : AppCompatActivity(), MusicListContract.MusicListView {
    private lateinit var binding: ActivityMusicListBinding
    private lateinit var presenter: MusicListContract.MusicListPresenter
    private val musicListAdapter by lazy { MusicListAdapter() }

    //on Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MusicListPresenterImpl(this, this)
        presenter.setList()


        binding.apply {
            cardViewCoverMusicLib.setOnClickListener {
                presenter.goToPlayingNow()
            }
            musicContainer.setOnClickListener {
                presenter.goToPlayingNow()
            }

        }

    }


    // on restart
    override fun onRestart() {
        super.onRestart()
        presenter = MusicListPresenterImpl(this,this)
        presenter.setList()
    }

    override fun showList() {
        if (StaticData.musicList.isNotEmpty()) {
            musicListAdapter.diff.submitList(StaticData.musicList)
            binding.recyclerTrack.adapter = musicListAdapter
            binding.recyclerTrack.layoutManager = LinearLayoutManager(this)
        } else {
            presenter.setError("list is empty")
        }
    }

    override fun showError(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun setCoverMusic() {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(StaticData.musicList[StaticData.position].data)
        val data = mmr.embeddedPicture

        if (data != null) {
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            binding.coverMusicLib.setImageBitmap(bitmap)
            binding.coverMusicLib.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            binding.coverMusicLib.setImageResource(R.drawable.cover)
            binding.coverMusicLib.scaleType = ImageView.ScaleType.FIT_CENTER

        }
    }
        override fun updateMeta() {
            binding.musicLibTitle.text = StaticData.musicList[StaticData.position].title
        }


    }