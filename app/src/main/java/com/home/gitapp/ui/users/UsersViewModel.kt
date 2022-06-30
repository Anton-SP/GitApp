package com.home.gitapp.ui.users

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import com.home.gitapp.utils.convertUserEntityToDAO
import com.home.gitapp.utils.downloadImageBitmap
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

    private val remoteUserRepo by lazy { NetUserRepoImp() }

    override val usersLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<UserEntity> = BehaviorSubject.create()
    override val usersNetUpdateLiveData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val usersBitmap: Observable<List<Bitmap>> = BehaviorSubject.create()

    override fun onRefresh() {
        loadLocalData()
    }

    private fun loadLocalData() {
        progressLiveData.toMutable().onNext(true)
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
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
            db.userDao().addUserList(list.map { convertUserEntityToDAO(it) })
        }.subscribeOn(Schedulers.io())
            .subscribe()

    }

    override fun onSaveImage(userList: List<UserEntity>) {
        Completable.fromRunnable {
            val listBitmap: MutableList<Bitmap> = mutableListOf()
            for (user in userList) {
                downloadImageBitmap(user.avatarUrl)?.let { listBitmap.add(it) }
            }
            usersBitmap.toMutable().onNext(listBitmap)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun loadData() {
        progressLiveData.toMutable().onNext(true)
        remoteUserRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { list ->
                    progressLiveData.toMutable().onNext(false)

                    usersNetUpdateLiveData.toMutable().onNext(list)

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


/*

// You can then apply all sorts of operation here
    Subscription subscription = clickEventObservable.flatMap(*/
/*  *//*
);

// Unsubscribe when you're done with it
    subscription.unsubscribe();
*/


}







