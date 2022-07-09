package com.home.gitapp.di

import com.example.dilibrary.Di
import com.home.gitapp.data.CacheUsersRepoImp
import com.home.gitapp.data.retrofit.GithubApi
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiModule(di: Di) {

    private val userRepo by lazy {
        CacheUsersRepoImp(
            LocalRepoImp(database.userDao()),
            NetUserRepoImp(gitApi)
        )
    }


    private val database by lazy { UserDatabase.getDatabase(di.getContext()) }

    val baseUrl = "https://api.github.com/"

    val gitApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .client(OkHttpClient.Builder().build())
        .build()
        .create(GithubApi::class.java)

    init {
        di.add(UserRepo::class, userRepo)
        di.add(UserDatabase::class, database)
    }
}