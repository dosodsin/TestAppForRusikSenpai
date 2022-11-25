package com.bormotov_vi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.CommentItemBinding
import com.bormotov_vi.model.comment.Comment

class CommentAdapter(
    private var comments: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)

        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        with(holder.binding) {
            commentNameTextView.text = comment.name
            commentBodyTextView.text = comment.body
            commentEmailTextView.text = comment.email
        }
    }

    override fun getItemCount(): Int = comments.size

    class CommentViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root)

}