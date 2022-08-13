package com.github.ebrahimi16153.music.fragment.alltrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.data.adapters.AllMusicAdapter
import com.github.ebrahimi16153.music.data.model.MusicFile
import com.github.ebrahimi16153.music.databinding.FragmentAllMusicBinding


class AllTrack : Fragment(),AllMusicContract.AllMusicView {
    private lateinit var binding: FragmentAllMusicBinding
    private lateinit var  presenter: AllMusicContract.AllMusicPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AllMusicPresenterImpl(requireContext(),this)
        presenter.setList()

    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }

    override fun setList(list: MutableList<MusicFile>) {
        val adapter = AllMusicAdapter()
        adapter.diff.submitList(list)
        binding.recyclerTrack.adapter = adapter
        binding.recyclerTrack.layoutManager = LinearLayoutManager(requireContext())
    }
}