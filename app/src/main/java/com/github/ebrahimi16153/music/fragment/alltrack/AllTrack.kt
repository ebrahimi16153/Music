package com.github.ebrahimi16153.music.fragment.alltrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.MusicListAdapter
import com.github.ebrahimi16153.music.databinding.FragmentAllTrackBinding


class AllTrack : Fragment(),TrackContract.TrackView {
    private lateinit var binding:FragmentAllTrackBinding
    private  lateinit var  presenter: TrackContract.TrackPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTrackBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init presenter
        presenter = TrackPresenterImpl(this)
        presenter.getList()


    }

    override fun setList() {

        binding.apply {
            val trackAdapter = MusicListAdapter()
            trackAdapter.diff.submitList(StaticData.allTrack)
            recyclerTrack.adapter = trackAdapter
            recyclerTrack.layoutManager = LinearLayoutManager(requireContext())

        }


    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }


}