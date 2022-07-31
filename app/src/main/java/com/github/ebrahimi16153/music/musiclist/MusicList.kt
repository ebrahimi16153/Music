package com.github.ebrahimi16153.music.musiclist

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.data.MusicFile
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.MusicListAdapter
import com.github.ebrahimi16153.music.databinding.ActivityMusicListBinding

class MusicList : AppCompatActivity(), MusicListContract.MusicListView {
    private lateinit var binding: ActivityMusicListBinding
    private lateinit var presenter: MusicListContract.MusicListPresenter
    private val musicListAdapter by lazy { MusicListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MusicListPresenterImpl(this,this)

        // check permission
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            presenter.setList()


        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2723)
        }



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


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 2723 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            presenter.setList()
        }
    }

}