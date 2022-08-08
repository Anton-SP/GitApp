package com.home.gitapp.ui.users

import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import java.util.*

class UserPresenterTest {

    @Mock
    private lateinit var presenter: UserPresenter


    @Mock
    private lateinit var userRepo: UserRepo

    @Mock
    private lateinit var view: UserContract.View

    @Mock
    private lateinit var userList:List<UserEntity>

    private var inProgress: Boolean = false





    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(userRepo)
    }


    @Test
    fun onRefresh_Test() {

        val presenter = mock<UserPresenter>()
        runBlocking {

            presenter.onRefresh()
        }
        runBlocking {
            presenter.onRefresh()
        }
        verifyBlocking(presenter, atLeastOnce()) { onRefresh() }
    }

    @Test
    fun verifyNetDataCall_Test(){

       runBlocking {
           Mockito.`when`(userRepo.getNetData()).thenReturn(userList)
           presenter.onRefresh()
       }

        runBlocking {
            verifyBlocking(userRepo, atLeastOnce()){getNetData()}
        }

    }

    @Test
    fun attach_Test(){
        val view = mock<UserContract.View>()
        Mockito.doNothing().`when`(view).showProgress(inProgress)
        Mockito.doNothing().`when`(view).showUsers(userList)
        presenter.attach(view)
        verify(view, times(2))
    }




}