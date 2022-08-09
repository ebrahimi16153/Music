package com.github.ebrahimi16153.music.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.databinding.ArtistItemBinding

class ArtistAdapter: RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val diff = AsyncListDiffer(this,differCallBack)

    inner class ViewHolder(private val binding: ArtistItemBinding): RecyclerView.ViewHolder(binding.root){

        fun setView(item:String){
            binding.artistName.text = item
            binding.root.setOnClickListener {
                StaticData.currentArtist.clear()
                StaticData.musicList.forEach(){
                    if (it.artist == item){

                        StaticData.currentArtist.add(it)

                        StaticData.apply {
                            if (motionLayoutArtist.currentState == R.id.start){
                                motionLayoutArtist.setTransition(R.id.start, R.id.end)
                                motionLayoutArtist.transitionToEnd()
                            }
                        }
                    }



                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ArtistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(diff.currentList[position])
    }

    override fun getItemCount() = diff.currentList.size
}