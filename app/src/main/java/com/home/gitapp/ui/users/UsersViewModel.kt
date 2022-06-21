package com.home.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject


class UsersViewModel(
    private val repository: UserRepo
) : UserContract.ViewModel {

    val remoteUserRepo by lazy { NetUserRepoImp() }

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<UserEntity> = BehaviorSubject.create()
    override val usersCashData: Observable<List<UserEntity>> = BehaviorSubject.create()

    override fun onRefresh() {
        loadLocalData()
    }

    private fun loadLocalData() {
        progressLiveData.toMutable().onNext(true)
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = { userList ->
                    if (userList.isNotEmpty()) {
                        progressLiveData.toMutable().onNext(false)
                        usersLiveData.toMutable().onNext(userList)
                    } else {
                        loadData()
                    }
                },
                onError = { error ->
                    progressLiveData.toMutable().onNext(false)
                    errorLiveData.toMutable().onNext(error)


                }
                    )
    }

    override fun onUserClick(userEntity: UserEntity) {
        openProfileLiveData.toMutable().onNext(userEntity)
    }

    override fun onNewData(db: UserDatabase, list: List<UserEntity>) {
        Completable.fromRunnable {
            db.userDao().addUserList(list.map { it.convertUserEntityToDAO() } )
        } .subscribeOn(Schedulers.io())
            .subscribe()

    }

    private fun loadData() {
        progressLiveData.toMutable().onNext(true)
        remoteUserRepo.getUsers()
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


}







