package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.di.AppModule
import com.home.gitapp.domain.UserRepo
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var database: UserDatabase

  /*  @AppModule.CacheRepo
    @Inject
    lateinit var userRepo: UserRepo*/


}

val Context.app: App get() = applicationContext as App
