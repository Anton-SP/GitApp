package com.home.gitapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
   // val appComponent: AppComponent by lazy {DaggerAppComponent.create()}

}

val Context.app: App get() = applicationContext as App
