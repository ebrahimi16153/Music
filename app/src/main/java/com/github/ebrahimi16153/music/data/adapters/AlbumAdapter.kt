package com.github.ebrahimi16153.music.data.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.model.AlbumMusic
import com.github.ebrahimi16153.music.databinding.AlbumItemBinding

class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {


    private val differCallBack = object :DiffUtil.ItemCallback<AlbumMusic>(){
        override fun areItemsTheSame(oldItem: AlbumMusic, newItem: AlbumMusic): Boolean {
          return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AlbumMusic, newItem: AlbumMusic): Boolean {
          return oldItem == newItem
        }

    }

   val diff = AsyncListDiffer(this,differCallBack)

    inner class ViewHolder(private val binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root){

        fun setView(item:AlbumMusic){
            binding.albumName.text = item.album
//            binding.root.setOnClickListener {
//                StaticData.currentAlbum.clear()
//                StaticData.musicList.forEach(){
//                    if (it.albume == item){
//
//                        StaticData.currentAlbum.add(it)
//
//                        StaticData.apply {
//                            if (motionLayoutAlbum.currentState == R.id.start){
//                                motionLayoutAlbum.setTransition(R.id.start, R.id.end)
//                                motionLayoutAlbum.transitionToEnd()
//                            }
//                        }
//                    }
//
//
//
//                }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(diff.currentList[position])
    }

    override fun getItemCount() = diff.currentList.size
}


