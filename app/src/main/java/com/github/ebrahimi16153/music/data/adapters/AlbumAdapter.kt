package com.github.ebrahimi16153.music.data.adapters


import android.os.Build
import android.provider.MediaStore
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.DataInteractor
import com.github.ebrahimi16153.music.data.model.Album
import com.github.ebrahimi16153.music.databinding.AlbumItemBinding


class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    private lateinit var  mListener:OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener:OnItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(diff.currentList[position])

    }

    override fun getItemCount() = diff.currentList.size


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }






    inner class ViewHolder(private val binding: AlbumItemBinding,listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

        // on item click listener
        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun setView(item: Album) {
            binding.albumName.text = item.album
            val data= DataInteractor(binding.root.context)
            val uri = data.getUri(item.albumId)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

                try {

                    val img = binding.root.context.contentResolver.loadThumbnail(uri, Size(200,200),null)
                    binding.albumItemImage.setImageBitmap(img)
                }catch (e:Exception){
                    binding.albumItemImage.setImageResource(R.drawable.cover100)
                }
            }
        }



    }




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



