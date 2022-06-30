package com.home.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.home.gitapp.R
import com.home.gitapp.databinding.ItemUserBinding
import com.home.gitapp.domain.UserEntity
import java.io.File

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
        /*
        не придумал как касиво проверить куда ведет ссылка в лок хранище или в сеть
        в итоге просто проверяю содежит ли путь http. если да то идем грузить из сети
        если нет то грузим из файла
         */
        if (userEntity.avatarUrl.contains("http")) {
            binding.userAvatarImageView.load(userEntity.avatarUrl)
        } else binding.userAvatarImageView.load(File(userEntity.avatarUrl))

        binding.userIdTextView.text = userEntity.id.toString()
        binding.userLoginTextView.text = userEntity.login
    }
}