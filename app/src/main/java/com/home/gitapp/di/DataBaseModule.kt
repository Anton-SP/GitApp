package com.home.gitapp.di

import android.content.Context
import androidx.room.Room
import com.home.gitapp.data.room.UserDao
import com.home.gitapp.data.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext appContext: Context): UserDatabase {
         //UserDatabase.getDatabase(appContext)
        return  Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "users_database"
        ).build()

           /* UserDatabase.INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "users_database"
                ).build()*/
    }

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }
}