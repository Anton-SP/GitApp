package com.home.gitapp.ui.user

import com.home.gitapp.domain.UserEntity

class UserDetailPresenter (
    private  val userEntity: UserEntity
        ):UserDetailContract.Presenter {

    private var view: UserDetailContract.View? = null

    private var user:UserEntity = userEntity



    override fun attach(view: UserDetailContract.View) {
        this.view
    }

    override fun detach() {
        view = null
    }

    override fun loadData() {
      view?.showUserDetail(user)
    }
}