package com.github.ebrahimi16153.music.data.adapters


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.github.ebrahimi16153.music.data.model.Album
import com.github.ebrahimi16153.music.databinding.AlbumItemBinding


class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(diff.currentList[position])
    }

    override fun getItemCount() = diff.currentList.size






    inner class ViewHolder(private val binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setView(item: Album) {
            binding.albumName.text = item.album
//            getCover(item, binding.albumItemImage)

        }
    }

//        private fun getCover(item: AlbumMusic, imageView: ImageView) {
//            val mmr = MediaMetadataRetriever()
//            mmr.setDataSource(item.cover)
//            val data = mmr.embeddedPicture
//
//            if (data != null) {
//                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
//                imageView.setImageBitmap(bitmap)
//                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//            }else{
//                imageView.setImageResource(R.drawable.cover100)
//            }
//
//        }


        private val differCallBack = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }

        }

        val diff = AsyncListDiffer(this, differCallBack)

    }



