package com.home.gitapp.domain

import io.reactivex.rxjava3.core.Single

interface UserRepo {


    fun getUsers(): Single<List<UserEntity>>


}

