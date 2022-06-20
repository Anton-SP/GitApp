package com.home.gitapp.utils

import com.home.gitapp.data.room.UserDao
import com.home.gitapp.data.room.RoomEntity
import com.home.gitapp.domain.UserEntity


    fun convertUserEntityToDao(userEntity: UserEntity) : RoomEntity =
        RoomEntity(userEntity.login,userEntity.id,userEntity.avatarUrl,userEntity.type,userEntity.siteAdmin)
