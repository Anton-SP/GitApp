package com.home.gitapp.data.room

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import com.home.gitapp.ui.MainActivity
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ActivityScoped
class LocalRepoImp @Inject constructor (
       private val userDao: UserDao
    ) : UserRepo {


    override fun getUsers(): Single<List<UserEntity>> =
        userDao.getAllUsers()
            .map { roomUserList -> roomUserList.map { it.convertDaoToUserEntity() } }

}

