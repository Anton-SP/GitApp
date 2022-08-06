package com.home.gitapp.utils

import com.home.gitapp.data.room.RoomUserEntity
import com.home.gitapp.domain.UserEntity


fun convertUserEntityToDAO(userEntity: UserEntity) =
    RoomUserEntity(
        userEntity.login,
        userEntity.id,
        userEntity.avatarUrl,
        userEntity.type,
        userEntity.siteAdmin
    )
