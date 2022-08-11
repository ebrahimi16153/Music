package com.github.ebrahimi16153.music.data.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.model.AlbumMusic
import com.github.ebrahimi16153.music.databinding.AlbumItemBinding
import java.io.IOException
import java.lang.reflect.InvocationTargetException

class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(diff.currentList[position])
    }

    override fun getItemCount() = diff.currentList.size






    inner class ViewHolder(private val binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root){

        fun setView(item:AlbumMusic) {
            binding.albumName.text = item.album
            getCover(item, binding.albumItemImage)



            binding.root.setOnClickListener {
                StaticData.currentAlbum.clear()
                StaticData.musicList.forEach() {
                    if (item.album == it.albume){
                        StaticData.currentAlbum.add(it)
                    }
                    StaticData.apply {
                            if (motionLayoutAlbum.currentState == R.id.start) {
                                motionLayoutAlbum.setTransition(R.id.start, R.id.end)
                                motionLayoutAlbum.transitionToEnd()
                            }
                        }

//                    if (it.albume == item.album) {
//
//                        StaticData.currentAlbum.add(it)
//
//                        StaticData.apply {
//                            if (motionLayoutAlbum.currentState == R.id.start) {
//                                motionLayoutAlbum.setTransition(R.id.start, R.id.end)
//                                motionLayoutAlbum.transitionToEnd()
//                            }
//                        }
//                    }


                }

            }
        }

        private fun getCover(item: AlbumMusic, imageView: ImageView){
            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(item.cover)
            val data = mmr.embeddedPicture

            if (data != null) {
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                imageView.setImageBitmap(bitmap)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        }




    private val differCallBack = object :DiffUtil.ItemCallback<AlbumMusic>(){
        override fun areItemsTheSame(oldItem: AlbumMusic, newItem: AlbumMusic): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AlbumMusic, newItem: AlbumMusic): Boolean {
            return oldItem == newItem
        }

    }

    val diff = AsyncListDiffer(this,differCallBack)


}


