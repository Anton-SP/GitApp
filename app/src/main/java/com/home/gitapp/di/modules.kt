package com.home.gitapp.di

import com.home.gitapp.data.CacheUsersRepoImp
import com.home.gitapp.data.retrofit.GithubApi
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.data.room.UserDao
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserRepo
import com.home.gitapp.ui.users.UsersViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    single { "https://api.github.com/" }

    single<GithubApi> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(get<String>())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(GithubApi::class.java)
    }


    single<UserDatabase> { UserDatabase.getDatabase(androidContext()) }
    single<UserDao> {get<UserDatabase>().userDao() }

    single<UserRepo>(named("remote")) { NetUserRepoImp(get()) }
    single<UserRepo>(named("local")) { LocalRepoImp(get<UserDao>()) }

    single<UserRepo>(named("cache")) {
        CacheUsersRepoImp(
            get(named("local")),
            get(named("remote")))
    }

    viewModel() { UsersViewModel(get(named("cache"))) }


}
