package com.bormotov_vi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.ItemUserBinding
import com.bormotov_vi.model.user.UsersItem


class UserAdapter(
    private var users: List<UsersItem>, private var userActionListener: UserActionListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface UserActionListener {
        fun onUserClickListener(user: UsersItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            userNameTextView.text = user.name
            userEmailTextView.text = user.email
            userCityTextView.text = user.address.city
            holder.itemView.setOnClickListener {
                userActionListener.onUserClickListener(user, position)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

}