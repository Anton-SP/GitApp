package com.home.gitapp

import android.app.Application
import android.content.Context
import com.example.dilibrary.Di
import com.example.dilibrary.DiImpl
import com.home.gitapp.di.DiModule

class App : Application() {

    lateinit var di: Di

    override fun onCreate() {
        super.onCreate()
        di = DiImpl(app).apply { DiModule(this) }

    }


}

val Context.app: App get() = applicationContext as App