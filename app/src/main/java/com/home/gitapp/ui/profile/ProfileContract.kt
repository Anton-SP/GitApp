package com.home.gitapp.ui.profile

import androidx.lifecycle.LiveData
import com.home.gitapp.domain.UserEntity

interface ProfileContract {

    interface ViewModel {
        val profileLiveData: LiveData<UserEntity>

        fun setProfile()
    }

}