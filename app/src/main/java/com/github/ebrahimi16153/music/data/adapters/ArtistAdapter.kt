package com.github.ebrahimi16153.music.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.databinding.ArtistItemBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

//    //onItemClickLister
//    private lateinit var mListener:OnItemClickListener
//
//    interface OnItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        mListener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ArtistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViews(diff.currentList[position])
    }

    override fun getItemCount() =diff.currentList.size

    inner class ViewHolder(val binding: ArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setViews(item: String) {
            binding.artistName.text = item
        }

//        init {
//            binding.root.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }

    }

    private val diffCalBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val diff = AsyncListDiffer(this,diffCalBack)

}