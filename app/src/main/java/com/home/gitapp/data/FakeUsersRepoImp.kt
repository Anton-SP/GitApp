package com.home.gitapp.data

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo

class FakeUsersRepoImp:UserRepo {

    private val data:List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4","user",false),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4","user",false),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4","user",false)
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        onSuccess(data)
    }
}