package com.home.gitapp.data.room

import androidx.room.Entity

@Entity
data class UserEntity(

    val login: String,

    val id: Long,

    val avatarUrl: String,

    val type: String,

    val siteAdmin: Boolean
)