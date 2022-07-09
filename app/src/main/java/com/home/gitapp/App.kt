package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.di.Di

class App : Application() {

    lateinit var di: Di

    override fun onCreate() {
        super.onCreate()
        di = Di(app)
    }


}

val Context.app: App get() = applicationContext as App