package com.home.gitapp.di

import com.home.gitapp.domain.UserRepo
import com.home.gitapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}