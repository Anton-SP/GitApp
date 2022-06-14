package com.home.gitapp.data.retrofit

import com.home.gitapp.domain.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getNetData(): Call<List<UserEntity>>
}