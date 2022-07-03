package com.home.gitapp.data.retrofit

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val gitApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .baseUrl("https://api.github.com/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(GithubApi::class.java)


class NetUserRepoImp : UserRepo {


    override fun getUsers(): Single<List<UserEntity>> = gitApi.getNetData().map { users ->
        users.map {
            it.convertDtoToUserEntity()
        }
    }

}
