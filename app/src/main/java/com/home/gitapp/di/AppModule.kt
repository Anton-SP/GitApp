package com.home.gitapp.di

import android.content.Context
import com.home.gitapp.data.CacheUsersRepoImp
import com.home.gitapp.data.retrofit.GithubApi
import com.home.gitapp.data.retrofit.NetUserRepoImp
import com.home.gitapp.data.room.LocalRepoImp
import com.home.gitapp.data.room.UserDao
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.domain.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val baseUrl = "https://api.github.com/"

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)
    @Retention(AnnotationRetention.SOURCE)
    @Qualifier
    annotation class RemoteRepo

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)
    @Retention(AnnotationRetention.SOURCE)
    @Qualifier
    annotation class LocalRepo

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
    @Qualifier
    annotation class CacheRepo


    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(GithubApi::class.java)
    }


    @Provides
    @Singleton
    fun provideDB (@ApplicationContext appContext: Context ):UserDatabase{
        return UserDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase):UserDao{
        return userDatabase.userDao()
    }

    @Provides
    @Singleton
    @RemoteRepo
    fun provideRemoteRepo(
        githubApi: GithubApi
    ): UserRepo {
        return NetUserRepoImp(githubApi)
    }

    @Provides
    @Singleton
    @LocalRepo
    fun provideLocalRepo(
        userDao: UserDao
    ): UserRepo {
       return LocalRepoImp(userDao)
    }

    @Provides
    @Singleton
    @CacheRepo
    fun provideCacheRepo(
        localRepo:UserRepo,
        remoteRepo: UserRepo
    ): UserRepo {
        return CacheUsersRepoImp(@LocalRepo localRepo, @RemoteRepo remoteRepo)
    }


}

/*  single<UserRepo>(named("remote")) { NetUserRepoImp(get()) }
    single<UserRepo>(named("local")) { LocalRepoImp(get<UserDao>()) }

    single<UserRepo>(named("cache")) {
        CacheUsersRepoImp(
            get(named("local")),
            get(named("remote"))
        )
    }


al appModule = module {


    single { "https://api.github.com/" }

    single<GithubApi> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(get<String>())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(GithubApi::class.java)
    }


    single<UserDatabase> { UserDatabase.getDatabase(androidContext()) }
    single<UserDao> { get<UserDatabase>().userDao() }



    viewModel() { UsersViewModel(get(named("cache"))) }


*/
