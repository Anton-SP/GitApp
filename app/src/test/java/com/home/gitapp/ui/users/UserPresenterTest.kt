package com.home.gitapp.ui.users

import com.home.gitapp.domain.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class UserPresenterTest {


    private lateinit var presenter: UserPresenter

    private var inProgress = false

    @Mock
    private lateinit var userRepo: UserRepo

    @Mock
    private lateinit var view: UserContract.View


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(userRepo)
    }


    @Test
    fun onRefresh_Test() {
     //   val mockPresenter = mock(UserPresenter::class)
        val presenter = mock<UserPresenter>()
        runBlocking {

            presenter.onRefresh()
        }
        runBlocking {
            presenter.onRefresh()
        }
        verifyBlocking(presenter, atLeastOnce()){onRefresh()}

        //runBlocking { verify(presenter) }


    }

/*
 @Test
    fun verifySuspendFunctionCalled() {
        /* Given */
        val m = mock<SomeInterface>()

        /* When */
        runBlocking { m.suspending() }

        /* Then */
        runBlocking { verify(m).suspending() }
    }


*/


}