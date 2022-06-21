package com.home.gitapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.UserDatabase

class App : Application() {
    //  val userRepo by lazy { FakeUsersRepoImp() }
    val userRepo by lazy { NetUserRepoImp() }

    val database by lazy {UserDatabase.getDatabase(this)}

}

val Context.app: App get() = applicationContext as App