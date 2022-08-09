package com.github.ebrahimi16153.music.fragment.artist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.AlbumAdapter
import com.github.ebrahimi16153.music.data.adapters.ArtistAdapter
import com.github.ebrahimi16153.music.data.adapters.CurrentPlayListAdapter
import com.github.ebrahimi16153.music.databinding.FragmentArtistMusicBinding

class ArtistMusic : Fragment(),ArtistContract.ArtistView {
 private lateinit var binding:FragmentArtistMusicBinding
 private lateinit var presenter:ArtistContract.ArtistPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistMusicBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter = ArtistPresenterImpl(this)
//        presenter.getList()
        setList()
        StaticData.motionLayoutArtist = binding.root
    }

    override fun setList() {
        val adapter = ArtistAdapter()
        adapter.diff.submitList(StaticData.artistList)
         binding.artistList.adapter = adapter
        binding.artistList.layoutManager = LinearLayoutManager(requireContext())
        binding.artistList.addOnScrollListener(StaticData.recyclerAnim)


        val adapter2 = CurrentPlayListAdapter()
        adapter2.diff.submitList(StaticData.currentArtist)
        binding.artistListTrack.adapter = adapter2
        binding.artistListTrack.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}