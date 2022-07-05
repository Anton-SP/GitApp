package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.di.AppComponent
import com.home.gitapp.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
   // val appComponent: AppComponent by lazy {DaggerAppComponent.create()}

}

val Context.app: App get() = applicationContext as App
