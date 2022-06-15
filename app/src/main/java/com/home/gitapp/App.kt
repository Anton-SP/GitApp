package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.data.retrofit.NetUserRepoImp

class App:Application() {
  //  val userRepo by lazy { FakeUsersRepoImp() }
    val userRepo by lazy { NetUserRepoImp() }

}

val Context.app: App get() = applicationContext as App