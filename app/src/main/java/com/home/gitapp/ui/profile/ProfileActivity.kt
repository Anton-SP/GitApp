package com.home.gitapp.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.home.gitapp.app
import com.home.gitapp.data.retrofit.UserEntityDto
import com.home.gitapp.databinding.ActivityProfileBinding
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.ui.DETAIL_USER

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var user: UserEntity

    private lateinit var profileViewModel: ProfileContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getParcelableExtra<UserEntityDto>(DETAIL_USER)?.let { user = it.convertDtoToUserEntity() }
        initViewModel()
        initView(user)

    }

    private fun initViewModel() {
        profileViewModel = getViewModel()
    }

    private fun getViewModel(): ProfileContract.ViewModel {
        return lastCustomNonConfigurationInstance as? ProfileContract.ViewModel
            ?: ProfileViewModel(user)
    }

    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.ViewModel {
        return profileViewModel
    }

    private fun initView(user: UserEntity?) {
        user?.let { showUserProfile(it) }
    }

    private fun showUserProfile(user: UserEntity) {
        binding.apply {
            profileAvatarImageView.load(user?.avatarUrl)
            profileLoginTextView.text = user?.login
            profileIdTextView.text = user?.id.toString()
            profileTypeTextView.text = user?.type
        }
    }


}