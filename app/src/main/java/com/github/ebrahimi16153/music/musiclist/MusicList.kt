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
    private val musicListAdapter by lazy { MusicListAdapter() }

    //on Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MusicListPresenterImpl(this, this)
        presenter.setList()


        binding.apply {
            cardViewCoverMusicLib.setOnClickListener {
                presenter.goToPlayingNow()
            }
            musicContainer.setOnClickListener {
                presenter.goToPlayingNow()
            }

        }


        // tab implement
//        binding.apply {
//            tabLayout.addTab(
//                tabLayout.newTab().setText("Musics").setIcon(R.drawable.ic_round_music_note_24)
//            )
//            tabLayout.addTab(
//                tabLayout.newTab().setText("Album").setIcon(R.drawable.ic_round_album_24)
//            )
//            tabLayout.addTab(
//                tabLayout.newTab().setText("Artist").setIcon(R.drawable.ic_round_person_24)
//            )
//        }


//      binding.viewPager.adapter = FmPagerAdapter(this,supportFragmentManager,binding.tabLayout.tabCount)


//        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
//        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                binding.viewPager.currentItem = tab!!.position
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })


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


    // on restart
    override fun onRestart() {
        super.onRestart()
        presenter = MusicListPresenterImpl(this, this)
        presenter.setList()
    }

    override fun showList() {
//        if (StaticData.musicList.isNotEmpty()) {
//            musicListAdapter.diff.submitList(StaticData.musicList)
//            binding.recyclerTrack.adapter = musicListAdapter
//            binding.recyclerTrack.layoutManager = LinearLayoutManager(this)
//        } else {
//            presenter.setError("list is empty")
//        }
    }

    override fun showError(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun setCoverMusic() {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(StaticData.musicList[StaticData.position].data)
        val data = mmr.embeddedPicture

        if (data != null) {
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            binding.coverMusicLib.setImageBitmap(bitmap)
            binding.coverMusicLib.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            binding.coverMusicLib.setImageResource(R.drawable.cover)
            binding.coverMusicLib.scaleType = ImageView.ScaleType.FIT_CENTER

        }
    }

    override fun updateMeta() {
        binding.musicLibTitle.text = StaticData.musicList[StaticData.position].title
    }


}