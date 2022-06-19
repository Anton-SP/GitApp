package com.home.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.home.gitapp.app
import com.home.gitapp.data.retrofit.UserEntityDto
import com.home.gitapp.databinding.ActivityMainBinding
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.ui.profile.ProfileActivity
import com.home.gitapp.ui.users.UserAdapter
import com.home.gitapp.ui.users.UserContract
import com.home.gitapp.ui.users.UsersViewModel

const val DETAIL_USER = "DETAIL_USER"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter { user ->

        val intent = Intent(this.app, ProfileActivity::class.java).apply {
           putExtra(DETAIL_USER,  UserEntityDto.convertUserEntityToDto(user))
        }
        startActivity(intent)
    }

    private lateinit var userViewModel: UserContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        userViewModel = getViewModel()

        userViewModel.progressLiveData.observe(this) { showProgress(it) }

        userViewModel.usersLiveData.observe(this) { showUsers(it) }

        userViewModel.errorLiveData.observe(this) { showError(it) }
    }

    private fun getViewModel(): UserContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UserContract.ViewModel
            ?: UsersViewModel(app.userRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): UserContract.ViewModel {
        return userViewModel
    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            userViewModel.onRefresh()
        }
        initRecycleView()

        showProgress(false)
    }


    private fun initRecycleView() {
        binding.mainActivityRecycle.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecycle.adapter = adapter
    }


    private fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)

    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(binding.root, throwable.message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.mainActivityProgressBar.isVisible = inProgress
        binding.mainActivityRecycle.isVisible = !inProgress
    }
}