package com.github.ebrahimi16153.music.musiclist

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.adapters.FmPagerAdapter
import com.github.ebrahimi16153.music.data.adapters.MusicListAdapter
import com.github.ebrahimi16153.music.databinding.ActivityMusicListBinding
import com.github.ebrahimi16153.music.fragment.album.AlbumMusic
import com.github.ebrahimi16153.music.fragment.alltrack.AllTrack
import com.github.ebrahimi16153.music.fragment.artist.ArtistMusic
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MusicList : AppCompatActivity(), MusicListContract.MusicListView {
    private lateinit var binding: ActivityMusicListBinding
    private lateinit var presenter: MusicListContract.MusicListPresenter

    //on Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicListBinding.inflate(layoutInflater)
        setContentView(binding.root)


       // init presenter
        presenter = MusicListPresenterImpl(this,this)
        presenter.setTabs()
        presenter.updateMetaData(binding.coverMusicLib,binding.musicLibTitle)

        binding.apply {
            cardViewCoverMusicLib.setOnClickListener {
                presenter.goToPlayingNow()
            }
            musicContainer.setOnClickListener {
                presenter.goToPlayingNow()
            }

        }

        StaticData.motionLayout = binding.root


    }

    override fun onBackPressed() {

        if (StaticData.motionLayoutAlbum.currentState == R.id.end){
            StaticData.apply {
                motionLayoutAlbum.setTransition(R.id.end,R.id.start)
                motionLayoutAlbum.transitionToEnd()
            }
        }else{
            finish()
        }
    }
    override fun onRestart() {
        super.onRestart()
        presenter.updateMetaData(binding.coverMusicLib,binding.musicLibTitle)
    }

    // implement tabLayout and page view
    override fun setTabs() {
        val listOfFragment: ArrayList<Fragment> = arrayListOf(
            AllTrack(), AlbumMusic(), ArtistMusic()
        )


        binding.viewPager.adapter = FmPagerAdapter(listOfFragment,this)
        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab,index ->
            tab.text = when(index){
                0->{"Music"}
                1->{"Album"}
                2->{"Artist"}
                else -> {throw Resources.NotFoundException("Potion not found")}
            }
            tab.setIcon(when(index) {
                0 -> {R.drawable.ic_round_music_note_24}
                1 -> {R.drawable.ic_round_album_24}
                2 -> {R.drawable.ic_round_person_24}

                else -> {throw Resources.NotFoundException("Potion not found") }
            })
        }.attach()

    }



    // set cover music


}