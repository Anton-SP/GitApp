package com.home.gitapp.data.room

import androidx.room.Query

interface UserDao {
    @Query("SELECT * FROM userEntity")
    fun getAllUsers():List<UserEntity>
}