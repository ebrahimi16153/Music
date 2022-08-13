package com.github.ebrahimi16153.music.fragment.album

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.AlbumAdapter
import com.github.ebrahimi16153.music.data.adapters.CurrentAlbumListAdapter
import com.github.ebrahimi16153.music.databinding.FragmentAlbumMusicBinding


class AlbumMusic : Fragment(), AlbumContract.AlbumView {

    lateinit var binding: FragmentAlbumMusicBinding
    private lateinit var presenter: AlbumContract.AlbumPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumMusicBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AlbumPresenterImpl(this)
        presenter.getList()





    }

    override fun setList() {

        val adapter = AlbumAdapter()
        val list = StaticData.albumList
        adapter.diff.submitList(list)
        adapter.setonItemClickListener(object : AlbumAdapter.OnItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(position: Int) {
                StaticData.apply {
                    currentAlbum.clear()
                    allTrack.forEach { music ->

                        if (music.albume == albumList[position].album) {
                            currentAlbum.add(music)
                        }
                    }
                    if (currentAlbum.size > 0) {

                        val trackAdapter = CurrentAlbumListAdapter()
                        trackAdapter.diff.submitList(currentAlbum)
                        binding.trackCurrentAlbumList.adapter = trackAdapter
                        binding.trackCurrentAlbumList.layoutManager =
                            LinearLayoutManager(requireContext())
                        trackAdapter.notifyDataSetChanged()

                        if (binding.root.currentState == R.id.start) {
                            binding.root.setTransition(R.id.start, R.id.end)
                            binding.root.transitionToEnd()
                        }
                    }
                }
            }
        })
        binding.listAlbum.adapter = adapter
        binding.listAlbum.layoutManager = LinearLayoutManager(requireContext())

        StaticData.motionLayoutAlbumMusic = binding.motionLayAlbum
    }


    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }

}