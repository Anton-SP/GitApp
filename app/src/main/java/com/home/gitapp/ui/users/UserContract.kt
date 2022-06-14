package com.home.gitapp.ui.users

import com.home.gitapp.domain.UserEntity

interface UserContract {

    interface View {
        fun showUsers(users: List<UserEntity>)

        fun showError(throwable: Throwable)

        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        suspend fun onRefresh()
    }
}