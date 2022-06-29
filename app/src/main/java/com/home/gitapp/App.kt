package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.data.room.UserDatabase

class App : Application() {
    //  val userRepo by lazy { FakeUsersRepoImp() }
    //val userRepo by lazy { NetUserRepoImp() }


    val userRepo by lazy { LocalRepoImp(database.userDao()) }

    val database by lazy {UserDatabase.getDatabase(this)}

}

val Context.app: App get() = applicationContext as App