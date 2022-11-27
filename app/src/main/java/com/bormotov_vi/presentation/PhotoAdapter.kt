package com.bormotov_vi.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.PhotoItemBinding
import com.bormotov_vi.domain.model.photo.Photo
import com.bumptech.glide.Glide

class PhotoAdapter(private val photos: List<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(inflater, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        with(holder.binding) {
            photoTitleText.text = photo.title
            Glide.with(photoImage.context)
                .load(photo.thumbnailUrl + ".jpg")
                .into(photoImage)
        }
    }

    override fun getItemCount() = photos.size

    class PhotoViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)
}