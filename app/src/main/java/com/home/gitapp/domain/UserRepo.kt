package com.home.gitapp.domain

interface UserRepo {

    fun getUsers(
        onSuccess:(List<UserEntity>) ->Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}