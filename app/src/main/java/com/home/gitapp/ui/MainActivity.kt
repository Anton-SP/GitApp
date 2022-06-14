package com.home.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.home.gitapp.app
import com.home.gitapp.databinding.ActivityMainBinding
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.ui.profile.ProfileActivity
import com.home.gitapp.ui.users.UserAdapter
import com.home.gitapp.ui.users.UserContract
import com.home.gitapp.ui.users.UserPresenter
import com.home.gitapp.ui.users.UsersViewModel

const val DETAIL_USER = "DETAIL_USER"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter { user ->

        Snackbar.make(binding.root, user.login, Snackbar.LENGTH_SHORT).show()
        val intent = Intent(this.app, ProfileActivity::class.java).apply {
            putExtra(DETAIL_USER, user)
        }
        startActivity(intent)
    }

    private lateinit var userViewModel:UserContract.ViewModel
  /*  private val userViewModel: UsersViewModel by viewModels {
        UsersViewModel.UsersViewModelFactory(app.userRepo)
    }*/

  //  private lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        presenter = extractPresenter()
        presenter.attach(this)

    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UserContract.Presenter? {
        return presenter
    }

    private fun extractPresenter(): UserContract.Presenter {
        return lastCustomNonConfigurationInstance as? UserContract.Presenter
            ?: UserPresenter(app.userRepo)
    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            this.lifecycle.coroutineScope.launchWhenStarted {
                presenter.onRefresh()
            }
        }
        initRecycleView()
    }

    private fun loadData() {
        userViewModel.requestUserList()

    }


    private fun initRecycleView() {
        binding.mainActivityRecycle.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecycle.adapter = adapter
    }


    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)

    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(binding.root, throwable.message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(inProgress: Boolean) {
        binding.mainActivityProgressBar.isVisible = inProgress
        binding.mainActivityRecycle.isVisible = !inProgress
    }
}