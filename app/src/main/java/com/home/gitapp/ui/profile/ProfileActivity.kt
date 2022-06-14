package com.home.gitapp.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.home.gitapp.databinding.ActivityProfileBinding
import com.home.gitapp.domain.UserEntity
import com.home.gitapp.ui.DETAIL_USER

class ProfileActivity : AppCompatActivity(), ProfileContract.View {

    private lateinit var binding: ActivityProfileBinding

    private lateinit var presenter: ProfileContract.Presenter

    private lateinit var user: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<UserEntity>(DETAIL_USER)?.let { user = it }

        presenter = extractPresenter()
        presenter.attach(this)

        initView(user)

    }

    private fun initView(user: UserEntity?) {
        user?.let { showUserDetail(it) }
    }

    override fun showUserDetail(user: UserEntity) {

        binding.apply {
            profileAvatarImageView.load(user?.avatarUrl)
            profileLoginTextView.text = user?.login
            profileIdTextView.text = user?.id.toString()
            profileTypeTextView.text = user?.type
        }
    }


    private fun extractPresenter(): ProfileContract.Presenter {
        return lastCustomNonConfigurationInstance as? ProfileContract.Presenter
            ?: ProfilePresenter(user)
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.Presenter? {
        return presenter
    }
}