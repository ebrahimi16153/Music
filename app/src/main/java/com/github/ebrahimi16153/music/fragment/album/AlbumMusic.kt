package com.github.ebrahimi16153.music.fragment.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ebrahimi16153.music.databinding.FragmentAlbumMusicBinding
import com.github.ebrahimi16153.music.databinding.FragmentAllTrackBinding


class AlbumMusic : Fragment() {

   private lateinit var binding:FragmentAlbumMusicBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumMusicBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            // implement views

    }


}