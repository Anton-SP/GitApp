package com.home.gitapp.ui.users

import androidx.lifecycle.LiveData
import com.home.gitapp.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UserContract {


    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<UserEntity>

        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
    }

}