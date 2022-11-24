package com.bormotov_vi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bormotov_vi.databinding.ItemUserBinding
import com.bormotov_vi.model.User

class UserAdapter(
    var users:ArrayList<User>
):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user=users[position]
        with(holder.binding){
            tvUserName.text=user.name
            tvUserEmail.text=user.email
            tvUserCity.text=user.city

        }
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(val binding:ItemUserBinding):RecyclerView.ViewHolder(binding.root)

}