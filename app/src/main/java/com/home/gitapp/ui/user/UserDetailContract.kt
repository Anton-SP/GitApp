package com.home.gitapp.ui.user

import com.home.gitapp.domain.UserEntity

interface UserDetailContract {

    interface View {
        fun showUserDetail(user:UserEntity)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        fun loadData()
    }
}