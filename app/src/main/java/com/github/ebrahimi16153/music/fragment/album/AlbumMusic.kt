package com.github.ebrahimi16153.music.fragment.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.data.adapters.AlbumAdapter
import com.github.ebrahimi16153.music.data.model.Album
import com.github.ebrahimi16153.music.databinding.FragmentAlbumMusicBinding


class AlbumMusic : Fragment(),AlbumMusicContract.AlbumView{

    lateinit var binding: FragmentAlbumMusicBinding
    lateinit var presenter: AlbumMusicContract.AlbumPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumMusicBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AlbumPresenterImpl(requireContext(),this)
        presenter.setList()

    }

    override fun setList(list: MutableList<Album>) {
        val adapter = AlbumAdapter()
        adapter.diff.submitList(list)
        binding.listAlbum.adapter = adapter
        binding.listAlbum.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}