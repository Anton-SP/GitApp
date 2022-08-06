package com.home.gitapp.data

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

class CacheUsersRepoImp : UserRepo {
    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        TODO("Not yet implemented")
    }

    override fun getUsers(): Single<List<UserEntity>> {
        TODO("Not yet implemented")
    }
}