package com.github.ebrahimi16153.music.fragment.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.DataInteractor
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.ArtistAdapter
import com.github.ebrahimi16153.music.data.adapters.CurrentArtistListAdapter
import com.github.ebrahimi16153.music.data.model.MusicFile
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
        StaticData.motionLayoutArtist = binding.motionLayArtist
        presenter = ArtistPresenterImpl(requireContext(),this)
        presenter.setList()

    }

    override fun setList(list: MutableList<String>) {

        val adapter = ArtistAdapter()
        adapter.diff.submitList(list)

        adapter.setOnItemClickListener(object :ArtistAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val data = DataInteractor(requireContext())
                val listArtist : MutableList<MusicFile> = mutableListOf()
                val artists : MutableList<MusicFile> = mutableListOf()
                data.getListOfMusic().forEach{
                    if (it.artist == list[position]){
                        artists.add(it)
                    }
                }
                listArtist.addAll(artists)

                val adapter2 = CurrentArtistListAdapter()
                adapter2.diff.submitList(listArtist)
                binding.trackCurrentArtistList.adapter = adapter2
                binding.trackCurrentArtistList.layoutManager = LinearLayoutManager(requireContext())


                if (binding.motionLayArtist.currentState == R.id.start){
                    binding.motionLayArtist.setTransition(R.id.start,R.id.end)
                    binding.motionLayArtist.transitionToEnd()
                }

            }

        })

        binding.listArtist.adapter = adapter
        binding.listArtist.layoutManager = LinearLayoutManager(requireContext())





    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }
}