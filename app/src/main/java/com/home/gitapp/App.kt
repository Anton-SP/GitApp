package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.data.FakeUsersRepoImp

class App:Application() {
    val userRepo by lazy { FakeUsersRepoImp() }
}

val Context.app: App get() = applicationContext as App