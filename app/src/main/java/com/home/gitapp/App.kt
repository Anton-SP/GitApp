package com.home.gitapp

import android.app.Application
import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Room
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.UserDatabase

class App : Application() {
    //  val userRepo by lazy { FakeUsersRepoImp() }
    val userRepo by lazy { NetUserRepoImp() }

    lateinit var usersDB: UserDatabase

    /*fun createDB() : UserDatabase =
     Room.databaseBuilder(app,UserDatabase::class.java,"users_database")
        .build()*/

    fun createDB() {
        usersDB = Room.databaseBuilder(app, UserDatabase::class.java, "roomEntity")
            .build()
    }

}

val Context.app: App get() = applicationContext as App