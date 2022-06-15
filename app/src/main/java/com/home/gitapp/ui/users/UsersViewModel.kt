package com.home.gitapp.ui.users

import androidx.lifecycle.*
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import com.home.gitapp.utils.SingleEventLiveData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class UsersViewModel(private val repository: UserRepo) : UserContract.ViewModel {

    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()


    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()


    override val progressLiveData: LiveData<Boolean> = MutableLiveData()


    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressLiveData.toMutable().postValue(true)
        repository.getUsers(
            onSuccess = { userList ->
                progressLiveData.toMutable().postValue(false)
                usersLiveData.toMutable().postValue(userList)
            },
            onError = { error ->
                progressLiveData.toMutable().postValue(false)
                errorLiveData.toMutable().postValue(error)
            }
        )
    }

    private fun <T> LiveData<T>.toMutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("not MutableLiveData")
    }

}







