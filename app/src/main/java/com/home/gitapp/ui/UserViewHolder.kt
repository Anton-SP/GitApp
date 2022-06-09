package com.home.gitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.home.gitapp.R
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.databinding.UserItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    private val binding = UserItemBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        binding.userItemAvatar.load(userEntity.avatarUrl)
        binding.userItemId.text = userEntity.id.toString()
        binding.userItemLogin.text = userEntity.login
    }
}