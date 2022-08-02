package com.github.ebrahimi16153.music.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.data.MusicFile
import com.github.ebrahimi16153.music.databinding.RowMusicListBinding

class MusicListAdapter : RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {


    //DiffUtil
    private val diffCallback = object : DiffUtil.ItemCallback<MusicFile>() {

        override fun areItemsTheSame(oldItem: MusicFile, newItem: MusicFile): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(oldItem: MusicFile, newItem: MusicFile): Boolean {
            return oldItem == newItem
        }

    }

    var diff = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RowMusicListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViews(diff.currentList[position])
    }

    override fun getItemCount() = diff.currentList.size


    class ViewHolder(private val binding: RowMusicListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setViews(item: MusicFile) {
            binding.titleRowMusic.text = item.title
            binding.artistRowMusic.text = item.artist
        }
    }

}