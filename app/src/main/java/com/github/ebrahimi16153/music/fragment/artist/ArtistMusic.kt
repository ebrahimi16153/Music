package com.github.ebrahimi16153.music.fragment.artist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.databinding.FragmentArtistMusicBinding

class ArtistMusic : Fragment() {
 private lateinit var binding:FragmentArtistMusicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistMusicBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}