package com.armin.hw14.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.armin.hw14.data.network.Model.Photo
import com.armin.hw14.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso


class SearchAdapter: ListAdapter<Photo, SearchAdapter.PhotoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        Log.d("H14 Debug", "onCreateViewHolder (Adapter) :")
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Log.d("H14 Debug", "onBindViewHolder (Adapter) :")
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class PhotoViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            Log.d("H14 Debug", " PhotoViewHolder (Adapter) :")
        }
        fun bind(photo: Photo) {
            Log.d("H14 Debug", "Give Data (Adapter) :")
            Log.d("H14 Debug", photo.title)
            binding.apply {
                Picasso.get().load(photo.url_s).into(imageViewItem);
                textViewIdItem.text = position.toString()
            }
        }

    }
}