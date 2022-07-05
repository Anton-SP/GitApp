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
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class InterfacesBindingModule {

    @Singleton
    @Binds
    abstract fun bindRemoteUserRepo(imp: NetUserRepoImp): UserRepo


/*
    @Qualifier
    annotation class RemoteRepoImpl

    @Qualifier
    annotation class LocalRepoImpl

    @Qualifier
    annotation class CacheRepoImpl

    @LocalRepoImpl
    @Singleton
    @Binds
    abstract fun bindLocalUserRepo(imp: LocalRepoImp): UserRepo

    @RemoteRepoImpl


    @CacheRepoImpl
    @Singleton
    @Binds
    abstract fun bindCacheUserRepo(imp: CacheUsersRepoImp): UserRepo*/
}