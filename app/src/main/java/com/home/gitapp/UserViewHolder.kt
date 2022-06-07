package com.home.gitapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.gitapp.databinding.UserItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    private val binding = UserItemBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        binding.userItemId.text = userEntity.id.toString()
        binding.userItemLogin.text = userEntity.login
    }
}