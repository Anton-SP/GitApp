package com.home.gitapp.data

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

internal class NetUserRepoImpTest {

    private lateinit var netUserRepoImp: NetUserRepoImp



    @Mock
    private val gitApi = Retrofit.Builder().build().create(UserRepo::class.java)

    @Before
    private fun setUp(){
        MockitoAnnotations.initMocks(this)
        netUserRepoImp = NetUserRepoImp()
    }

    @Test
    suspend fun getNetData_Test(){

        val userRepo = listOf(mock(UserEntity::class.java))

        `when`(netUserRepoImp.getNetData()).thenReturn(userRepo)
        `when`(userRepo.count()).thenReturn(36)

        netUserRepoImp.getNetData()
        verify(userRepo).count()


    }

}