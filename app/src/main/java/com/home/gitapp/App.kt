package com.home.gitapp

import android.app.Application
import android.content.Context
import com.home.gitapp.data.CacheUsersRepoImp
import com.home.gitapp.data.retrofit.GithubApi
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.data.room.UserDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {


    val userRepo by lazy {
        CacheUsersRepoImp(
            LocalRepoImp(database.userDao()),
            NetUserRepoImp(gitApi)
        )
    }


    val database by lazy { UserDatabase.getDatabase(this) }

    val baseUrl = "https://api.github.com/"

    val gitApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .client(OkHttpClient.Builder().build())
        .build()
        .create(GithubApi::class.java)


}

val Context.app: App get() = applicationContext as App