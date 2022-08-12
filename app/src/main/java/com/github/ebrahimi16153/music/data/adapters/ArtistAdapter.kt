package com.github.ebrahimi16153.music.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData
import com.github.ebrahimi16153.music.data.model.AlbumMusic
import com.github.ebrahimi16153.music.databinding.ArtistItemBinding


 class ArtistAdapter:RecyclerView.Adapter<ArtistAdapter.ViewHolder>(){

     // on Item Click listener
     private lateinit var  mListener:OnItemClickListener

     interface OnItemClickListener{
         fun onItemClick(position: Int)
     }

     fun setonItemClickListener(listener:OnItemClickListener){
         mListener = listener
     }


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(ArtistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),mListener)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.setView(diff.currentList[position])
     }

     override fun getItemCount() = diff.currentList.size
     inner class ViewHolder(val binding: ArtistItemBinding ,listener: OnItemClickListener):RecyclerView.ViewHolder(binding.root){

         // on item click listener

         init {
             binding.root.setOnClickListener {
                 listener.onItemClick(adapterPosition)
             }
         }

        fun setView(item:String){
            binding.artistName.text = item
            binding.root.setOnClickListener {
             StaticData.apply {
                 currentArtist.clear()
                 allTrack.forEach { musicFile ->
                     if (musicFile.artist == item){
                         currentArtist.add(musicFile)
                     }
                 }


             }

                }

            }
        }


     //difUtil

     private val differCallBack = object : DiffUtil.ItemCallback<String>() {
         override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
             return oldItem == newItem
         }

         override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
             return oldItem == newItem
         }

     }

     val diff = AsyncListDiffer(this, differCallBack)

 }




//class ArtistAdapter: RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {
//
//    // on Item Click listener
//    private lateinit var  mListener:OnItemClickListener
//
//    interface OnItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setonItemClickListener(listener:OnItemClickListener){
//        mListener = listener
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(ArtistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),mListener)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.setView(diff.currentList[position])
//    }
//
//    override fun getItemCount() = diff.currentList.size
//
//
//
//
//    inner class ViewHolder(private val binding: ArtistItemBinding,listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root){
//
//
//        // on item click listener
//        init {
//            binding.root.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }
//
//        fun setView(item:String){
//            binding.artistName.text = item
//            binding.root.setOnClickListener {
//             StaticData.apply {
//                 currentArtist.clear()
//                 allTrack.forEach { musicFile ->
//                     if (musicFile.artist == item){
//                         currentArtist.add(musicFile)
//                     }
//                 }
//
//
//             }
//
//                }
//
//            }
//        }
//
//
//
//    // diffUtil
//
//
//    private val differCallBack = object : DiffUtil.ItemCallback<String>(){
//        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
//            return  oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//    val diff = AsyncListDiffer(this,differCallBack)
//}