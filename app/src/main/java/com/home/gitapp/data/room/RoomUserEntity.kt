package com.home.gitapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.home.gitapp.data.retrofit.UserEntityDto
import com.home.gitapp.domain.UserEntity

@Entity
data class RoomUserEntity(
    @PrimaryKey
    val login: String,

    val id: Long,

    val avatarUrl: String,

    val type: String,

    val siteAdmin: Boolean
) {
    fun convertDaoToUserEntity() = UserEntity(login, id, avatarUrl, type, siteAdmin)

    companion object {
        fun convertUserEntityToDao(userEntity: UserEntity): RoomUserEntity {
            return RoomUserEntity(
                userEntity.login,
                userEntity.id,
                userEntity.avatarUrl,
                userEntity.type,
                userEntity.siteAdmin
            )
        }
    }
}

