package com.home.gitapp.ui.profile

import com.home.gitapp.domain.UserEntity

class ProfilePresenter(
    private val userEntity: UserEntity
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    private var user: UserEntity = userEntity


    override fun attach(view: ProfileContract.View) {
        this.view
    }

    override fun detach() {
        view = null
    }

    override fun loadData() {
        view?.showUserDetail(user)
    }
}