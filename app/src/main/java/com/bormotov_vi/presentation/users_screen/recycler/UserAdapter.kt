package com.bormotov_vi.presentation.users_screen.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.ItemUserBinding
import com.bormotov_vi.domain.model.user.UsersItem

class UserAdapter(
    private var users: List<UsersItem>,
    private var userActionListener: (UsersItem) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

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
                userActionListener(user)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}