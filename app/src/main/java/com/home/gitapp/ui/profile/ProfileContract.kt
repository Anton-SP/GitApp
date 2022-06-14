package com.home.gitapp.ui.profile

import com.home.gitapp.domain.UserEntity

interface ProfileContract {

    interface View {
        fun showUserDetail(user: UserEntity)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        fun loadData()
    }
}