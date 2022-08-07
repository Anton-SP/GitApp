package com.home.gitapp.ui.users


import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo

class UserPresenter(

    private val userRepo: UserRepo

) : UserContract.Presenter {
    private var view: UserContract.View? = null

    private var userList: List<UserEntity>? = null

    private var inProgress: Boolean = false


    override fun attach(view: UserContract.View) {
        this.view = view
        view.showProgress(inProgress)
        userList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override suspend fun onRefresh() {
      //  loadData()
        view?.showProgress(true)
        inProgress = true
        userRepo.getNetData().let {
            view?.showProgress(false)
            view?.showUsers(it)
            userList = it
            inProgress = false
        }
    }

    private suspend fun loadData() {
        view?.showProgress(true)
        inProgress = true
        userRepo.getNetData().let {
            view?.showProgress(false)
            view?.showUsers(it)
            userList = it
            inProgress = false
        }
    }
}

