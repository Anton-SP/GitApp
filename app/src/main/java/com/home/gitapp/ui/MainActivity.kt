package com.home.gitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.home.gitapp.app
import com.home.gitapp.databinding.ActivityMainBinding
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.domain.UserRepo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter()
    private val usersRepo:UserRepo by lazy { app.userRepo}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initViews()

        initRecycleView()

    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            Snackbar.make(binding.root, "Test Click", Snackbar.LENGTH_SHORT).show()
            loadData()
        }
    }

    private fun loadData() {
        usersRepo.getUsers(
            onSuccess = {onDataLoaded(it)},
            onError = {onError(it)}
        )
    }

    private fun onDataLoaded(data: List<UserEntity>) {
        adapter.setData(data)
    }

    private fun onError(throwable: Throwable){
        Snackbar.make(binding.root, throwable.message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun initRecycleView() {
        binding.mainActivityRecycle.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecycle.adapter = adapter
    }
}