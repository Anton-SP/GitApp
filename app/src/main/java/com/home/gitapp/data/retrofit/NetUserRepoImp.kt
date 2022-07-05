package com.home.gitapp.data.retrofit

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@ActivityScoped
class NetUserRepoImp @Inject constructor(val githubApi: GithubApi) : UserRepo {


    override fun getUsers(): Single<List<UserEntity>> = githubApi.getNetData().map { users ->
        users.map {
            it.convertDtoToUserEntity()
        }
    }

}
