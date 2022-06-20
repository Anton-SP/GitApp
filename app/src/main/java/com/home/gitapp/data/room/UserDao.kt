package com.home.gitapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM roomEntity")
    fun getAllUsers(): List<RoomEntity>

    @Insert
    fun insert(roomEntity:RoomEntity)

    @Query("DELETE FROM roomEntity")
    fun clearAllData()

}