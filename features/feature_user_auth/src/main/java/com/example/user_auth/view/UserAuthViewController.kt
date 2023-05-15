package com.example.user_auth.view

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import com.example.user_auth.viewModel.UserAuthViewModel
import com.example.user_auth.databinding.FragmentUserAuthBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class UserAuthViewController @AssistedInject constructor(
    @Assisted("authFragmentBinding") private val binding: FragmentUserAuthBinding,
    @Assisted("authLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("authViewModel") private val viewModel: UserAuthViewModel
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("authFragmentBinding") binding: FragmentUserAuthBinding,
            @Assisted("authLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("authViewModel") viewModel: UserAuthViewModel
        ): UserAuthViewController
    }

    fun setupViews() {
        setupErrorTitle()
        setupLogin()
        setupPassword()
        setupSighIn()
        setupSighOut()
    }

    private fun setupErrorTitle() {
        viewModel.observeError(lifecycleOwner) { error ->
            //TODO
//            binding.tvError.text = error
//            binding.tvError.visibility = View.VISIBLE
        }
    }

    private fun setupLogin() {
        viewModel.observeErrorLogin(lifecycleOwner) { error ->
            binding.tilLogin.error = error
        }
        binding.etLogin.doAfterTextChanged {
            if (binding.etLogin.hasFocus()) {
                viewModel.setLogin(it.toString())
                binding.tilLogin.isErrorEnabled = false
            }
        }
    }

    private fun setupPassword() {
        viewModel.observeErrorPassword(lifecycleOwner) { error ->
            binding.tilPassword.error = error
        }
        binding.etPassword.doAfterTextChanged {
            if (binding.etPassword.hasFocus()) {
                viewModel.setPassword(it.toString())
                binding.tilPassword.isErrorEnabled = false
            }
        }
    }

    private fun setupSighIn() {
        binding.btnSingIn.setOnClickListener {
            viewModel.signInWithEmail()
            //TODO
//            binding.tvError.visibility = View.GONE
        }

    }

    private fun setupSighOut() {
        binding.btnSingUp.setOnClickListener {
            viewModel.signUpWithEmail()
            //TODO
//            binding.tvError.visibility = View.GONE
        }

    }

}