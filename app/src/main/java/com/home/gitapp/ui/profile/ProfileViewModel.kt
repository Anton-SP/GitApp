package com.home.gitapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home.gitapp.domain.UserEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class ProfileViewModel(private val user: UserEntity) : ProfileContract.ViewModel {

        override val profileLiveData: Observable<UserEntity> = BehaviorSubject.create()

        override fun setProfile() {
               profileLiveData.toMutable().onNext(user)
        }

        private fun <T : Any> Observable<T>.toMutable(): Subject<T> {
                return this as? Subject<T>
                        ?: throw IllegalStateException("It is not Mutable")
        }
}