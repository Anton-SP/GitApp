package com.home.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.home.gitapp.R
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.databinding.ItemUserBinding

class UserViewHolder(
    parent: ViewGroup,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(userEntity: UserEntity) {
        binding.userAvatarImageView.load(userEntity.avatarUrl)
        binding.userIdTextView.text = userEntity.id.toString()
        binding.userLoginTextView.text = userEntity.login
    }
}