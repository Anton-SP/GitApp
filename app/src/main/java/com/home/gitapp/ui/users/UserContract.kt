package com.home.gitapp.ui.users

import androidx.lifecycle.LiveData
import com.home.gitapp.domain.UserEntity

interface UserContract {


    interface ViewModel {
        val usersLiveData: LiveData<List<UserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh()
    }

}

/* interface View {
       fun showUsers(users: List<UserEntity>)

       fun showError(throwable: Throwable)

       fun showProgress(inProgress: Boolean)
   }

   interface Presenter {
       fun attach(view: View)

       fun detach()

       suspend fun onRefresh()
   }*/