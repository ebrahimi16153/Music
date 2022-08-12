package com.github.ebrahimi16153.music.fragment.artist

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
import com.github.ebrahimi16153.music.data.adapters.ArtistAdapter
import com.github.ebrahimi16153.music.data.adapters.CurrentAlbumListAdapter
import com.github.ebrahimi16153.music.data.adapters.CurrentArtistListAdapter
import com.github.ebrahimi16153.music.databinding.FragmentArtistMusicBinding

class ArtistMusic : Fragment(), ArtistContract.ArtistView {
    private lateinit var binding: FragmentArtistMusicBinding
    private lateinit var presenter: ArtistContract.ArtistPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        back()
        binding = FragmentArtistMusicBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter = ArtistPresenterImpl(this)
//        presenter.getList()
        setList()
    }

    override fun setList() {
        val adapter = ArtistAdapter()
        adapter.diff.submitList(StaticData.artistList)
        adapter.setOnItemClickListener(object :ArtistAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

                StaticData.apply {
                    currentArtist.clear()
                    allTrack.forEach {
                        if(it.artist == artistList[position]){
                            currentArtist.add(it)
                        }
                    }
                }

                val adapter2 = CurrentArtistListAdapter()
                adapter2.diff.submitList(StaticData.currentArtist)
                binding.artistListTrack.adapter = adapter2
                binding.artistListTrack.layoutManager = LinearLayoutManager(requireContext())


                if (binding.root.currentState == R.id.start){
                    binding.root.setTransition(R.id.start,R.id.end)
                    binding.root.transitionToEnd()
                }

            }

        })
        binding.artistList.adapter = adapter
        binding.artistList.layoutManager = LinearLayoutManager(requireContext())

        //set recycler artist track


    }

    override fun onError(massage: String) {
        Toast.makeText(requireContext(), massage, Toast.LENGTH_SHORT).show()
    }

    private fun back(){

        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (binding.root.currentState == R.id.end){
                    binding.root.setTransition(R.id.end,R.id.start)
                    binding.root.transitionToEnd()
                }else{
                    requireActivity().finish()
                }
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)

    }

}