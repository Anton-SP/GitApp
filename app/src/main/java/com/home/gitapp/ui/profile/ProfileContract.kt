package com.home.gitapp.ui.profile

import com.home.gitapp.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface ProfileContract {

    interface ViewModel {
        val profileLiveData: Observable<UserEntity>

        fun setProfile()
    }

}