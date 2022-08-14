package com.github.ebrahimi16153.music.fragment.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.adapters.ArtistAdapter
import com.github.ebrahimi16153.music.databinding.ArtistItemBinding
import com.github.ebrahimi16153.music.databinding.FragmentArtistMusicBinding

class ArtistMusic : Fragment(),ArtistContract.ArtistView {

lateinit var binding: FragmentArtistMusicBinding
lateinit var presenter: ArtistContract.ArtistPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArtistMusicBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ArtistPresenterImpl(requireContext(),this)
        presenter.setList()

    }

    override fun setList(list: MutableList<String>) {

        val adapter = ArtistAdapter()
        adapter.diff.submitList(list)
        binding.listArtist.adapter = adapter
        binding.listArtist.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}