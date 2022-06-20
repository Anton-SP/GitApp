package com.home.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import com.home.gitapp.utils.convertUserEntityToDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject


class UsersViewModel(private val repository: UserRepo) : UserContract.ViewModel {

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<UserEntity> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userEntity: UserEntity) {
        openProfileLiveData.toMutable().onNext(userEntity)
    }

    private fun loadData() {
        progressLiveData.toMutable().onNext(true)
        repository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { list ->
                    progressLiveData.toMutable().onNext(false)
                    usersLiveData.toMutable().onNext(list)
                },
                onError = { error ->
                    progressLiveData.toMutable().onNext(false)
                    errorLiveData.toMutable().onNext(error)
                }
            )

    }

    private fun <T> LiveData<T>.toMutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("not MutableLiveData")
    }

    private fun <T : Any> Observable<T>.toMutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not Mutable o_O")
    }

    fun fillDB(db:UserDatabase,userList: List<UserEntity>){
        for (user in userList) {
            db.userDao().insert(convertUserEntityToDao(user))
        }

    }
}







