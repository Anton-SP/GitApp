package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.data.CacheUsersRepoImp
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.data.room.UserDatabase

class App : Application() {


    val userRepo by lazy {
        CacheUsersRepoImp(
            LocalRepoImp(database.userDao()),
            NetUserRepoImp()
        )
    }

    val database by lazy { UserDatabase.getDatabase(this) }

}

val Context.app: App get() = applicationContext as App