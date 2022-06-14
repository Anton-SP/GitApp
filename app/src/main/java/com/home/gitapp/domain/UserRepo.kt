package com.home.gitapp.domain

import retrofit2.Call
import retrofit2.http.GET

interface UserRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

}

