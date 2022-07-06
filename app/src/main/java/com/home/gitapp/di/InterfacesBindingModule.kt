package com.home.gitapp.di

import com.home.gitapp.data.CacheUsersRepoImp
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.domain.UserRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Qualifier

@InstallIn(ActivityComponent::class)
@Module
abstract class InterfacesBindingModule {
    @Qualifier
    annotation class RemoteRepoImpl

    @Qualifier
    annotation class LocalRepoImpl

    @Qualifier
    annotation class CacheRepoImpl

    @RemoteRepoImpl
    @Binds
    abstract fun bindRemoteUserRepo(imp: NetUserRepoImp): UserRepo

    @LocalRepoImpl
    @Binds
    abstract fun bindLocalUserRepo(imp: LocalRepoImp): UserRepo

    @CacheRepoImpl
    @Binds
    abstract fun bindCacheUserRepo(imp: CacheUsersRepoImp): UserRepo

}