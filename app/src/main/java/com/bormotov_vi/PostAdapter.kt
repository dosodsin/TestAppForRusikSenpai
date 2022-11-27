package com.bormotov_vi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.ItemPostsBinding
import com.bormotov_vi.model.post.UserPostItem

class PostAdapter(
    private var posts: List<UserPostItem>, private var postActionListener: PostActionListener
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface PostActionListener {
        fun onPostClickListener(post: UserPostItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostsBinding.inflate(inflater, parent, false)

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        with(holder.binding) {
            postBodyTextView.text = post.title
            postTitleTextView.text = post.body
            holder.itemView.setOnClickListener {
                postActionListener.onPostClickListener(post, position)
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(val binding: ItemPostsBinding) : RecyclerView.ViewHolder(binding.root)
}