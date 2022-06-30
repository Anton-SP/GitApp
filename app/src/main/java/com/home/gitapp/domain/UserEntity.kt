package com.home.gitapp.domain

import com.home.gitapp.data.room.RoomUserEntity


data class UserEntity(

    val login: String,

    val id: Long,

    val avatarUrl: String,

    val type: String,

    val siteAdmin: Boolean
)
{
    fun convertUserEntityToDAO() = RoomUserEntity(login, id, avatarUrl, type, siteAdmin)
}