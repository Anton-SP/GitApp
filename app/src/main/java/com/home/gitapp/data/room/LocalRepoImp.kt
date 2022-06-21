package com.home.gitapp.data.room

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

class LocalRepoImp(private val userDao: UserDao) : UserRepo {
    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        TODO("Not yet implemented")
    }

    override fun getUsers(): Single<List<UserEntity>> =
        userDao.getAllUsers()
            .map { roomUserList -> roomUserList.map { it.convertDaoToUserEntity() } }


}

