package com.home.gitapp.data.retrofit

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val gitApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api.github.com/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(GithubApi::class.java)


class NetUserRepoImp : UserRepo {

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        gitApi.getNetData().enqueue(object : Callback<List<UserEntityDto>> {
            override fun onResponse(
                call: Call<List<UserEntityDto>>,
                response: Response<List<UserEntityDto>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body.map { it.convertDtoToUserEntity() })
                } else {
                    onError?.invoke(IllegalStateException("error loading data"))
                }
            }

            override fun onFailure(call: Call<List<UserEntityDto>>, t: Throwable) {
                onError?.invoke(t)
            }

        })

    }

}
