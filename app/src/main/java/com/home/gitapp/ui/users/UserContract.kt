package com.home.gitapp.ui.users

import android.content.Context
import android.graphics.Bitmap
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UserContract {


    interface ViewModel {
        val usersLiveData: Observable<List<UserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<UserEntity>
        val usersNetUpdateLiveData: Observable<List<UserEntity>>
        val usersBitmap: Observable<List<Bitmap>>
        fun onRefresh()
        fun onUserClick(userEntity: UserEntity)
        fun onNewData(db:UserDatabase,list:List<UserEntity>)
        fun onSaveImage(userList:List<UserEntity>)
    }

}