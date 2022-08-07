package com.home.gitapp.ui.users

import com.home.gitapp.domain.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

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
    fun attach_Test() {
        presenter.attach(view)
        Mockito.verify(view, Mockito.times(1)).showProgress(true)
    }

    @Test
    fun detach() {
    }

    @Test
    fun onRefresh() = runBlocking {
        presenter.onRefresh()
        Mockito.verify(userRepo, Mockito.atLeastOnce()).getNetData()
    }
}