package com.home.gitapp.ui

import com.home.gitapp.domain.UserEntity

interface UserContract {

    interface View {
        fun showUsers(users: List<UserEntity>)

        fun showError(throwable: Throwable)

        fun showPorgress(inProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        suspend fun onRefresh()
    }
}