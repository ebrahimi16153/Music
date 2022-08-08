package com.github.ebrahimi16153.music.fragment.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.AlbumAdapter
import com.github.ebrahimi16153.music.data.adapters.CurrentPlayListAdapter
import com.github.ebrahimi16153.music.databinding.FragmentAlbumMusicBinding


class AlbumMusic : Fragment(),AlbumContract.AlbumView {

   private lateinit var binding:FragmentAlbumMusicBinding
   private lateinit var presenter: AlbumContract.AlbumPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumMusicBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StaticData.motionLayoutAlbum = binding.root
        presenter = AlbumPresenterImpl(requireContext(),this)
        presenter.getList()

    }

    override fun setList() {
       val adapter = AlbumAdapter()
        adapter.diff.submitList(StaticData.albumList)
        binding.listAlbum.adapter = adapter
        binding.listAlbum.layoutManager = LinearLayoutManager(requireContext())


        val trackAdapter = CurrentPlayListAdapter()
        trackAdapter.diff.submitList(StaticData.currentAlbum)
        binding.trackCurrentAlbumList.adapter = trackAdapter
        binding.trackCurrentAlbumList.layoutManager =LinearLayoutManager(requireContext())
    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }


}