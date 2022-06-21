package com.home.gitapp.ui.users

import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UserContract {


    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<UserEntity>
        val usersCashData: Observable<List<UserEntity>>
        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
        fun onNewData(db:UserDatabase,list:List<UserEntity>)
    }

}