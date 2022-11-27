package com.bormotov_vi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.AlbumItemBinding
import com.bormotov_vi.model.album.Album

class AlbumsAdapter(
    private val albums: List<Album>, private var albumActionListener: (Album) -> Unit
) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlbumItemBinding.inflate(inflater, parent, false)

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        with(holder.binding) {
            albumTextView.text = album.title
            holder.itemView.setOnClickListener {
                albumActionListener(album)
            }
        }
    }

    override fun getItemCount() = albums.size

    class AlbumViewHolder(val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface AlbumActionListener {
        fun onAlbumClickListener(album: Album, position: Int)
    }
}