package com.home.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.home.gitapp.app
import com.home.gitapp.data.retrofit.UserEntityDto
import com.home.gitapp.data.room.UserDatabase
import com.home.gitapp.databinding.ActivityMainBinding
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.ui.profile.ProfileActivity
import com.home.gitapp.ui.users.UserAdapter
import com.home.gitapp.ui.users.UsersViewModel
import com.home.gitapp.utils.getImagePath
import com.home.gitapp.utils.observableClickListener
import com.home.gitapp.utils.onLoadBitmap
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable


const val DETAIL_USER = "DETAIL_USER"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter { user ->
        userViewModel.onUserClick(user)
    }

    private val userViewModel: UsersViewModel by viewModels()

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()

    }


    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun openProfileScreen(userEntity: UserEntity) {
        val intent = Intent(this.app, ProfileActivity::class.java).apply {
            putExtra(DETAIL_USER, UserEntityDto.convertUserEntityToDto(userEntity))
        }
        startActivity(intent)
    }

    private fun initViews() {
        initRecycleView()
        showProgress(false)
    }

    private fun initViewModel() {

        viewModelDisposable.addAll(

            userViewModel.progressLiveData.subscribe { showProgress(it) },
            userViewModel.usersLiveData.subscribe {
                showUsers(it)
                checkData(it)
            },
            userViewModel.usersNetUpdateLiveData.subscribe {
                setCacheData(it)
            },
            userViewModel.errorLiveData.subscribe { showError(it) },
            userViewModel.openProfileLiveData.subscribe { openProfileScreen(it) },

            binding.mainActivityRefreshButton.observableClickListener().subscribe {
                userViewModel.onRefresh()
            }
        )
    }

    private fun checkData(userList: List<UserEntity>) {
        userList.let {
            userViewModel.compareData(app.database, userList)
        }

    }


    fun setCacheData(userList: List<UserEntity>) {
        userViewModel.onSaveImage(userList)
        userViewModel.usersBitmap.subscribe { bitmapList ->
            var tmpUserList: MutableList<UserEntity> = mutableListOf()
            for (i in 0..userList.size - 1) {
                onLoadBitmap(
                    app,
                    bitmapList[i],
                    userList[i].login
                )
                val internalPath = getImagePath(app, userList[i].login)
                val updatedUser = UserEntity(
                    userList[i].login,
                    userList[i].id,
                    "$internalPath.png",
                    userList[i].type,
                    userList[i].siteAdmin
                )
                tmpUserList.add(updatedUser)
            }
            updateLocalRepo(app.database, tmpUserList)
        }

    }


    private fun updateLocalRepo(db: UserDatabase, userList: List<UserEntity>) {
        userViewModel.onNewData(db, userList)
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