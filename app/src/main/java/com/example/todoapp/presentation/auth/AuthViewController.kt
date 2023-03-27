package com.example.todoapp.presentation.auth

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentAuthBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewController @AssistedInject constructor(
    @Assisted("authFragmentBinding") private val binding: FragmentAuthBinding,
    @Assisted("authLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("authViewModel") private val viewModel: AuthViewModel
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("authFragmentBinding") binding: FragmentAuthBinding,
            @Assisted("authLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("authViewModel") viewModel: AuthViewModel
        ): AuthViewController
    }

    fun setupViews() {
        setupErrorTitle()
        setupLogin()
        setupPassword()
        setupSighIn()
        setupSighOut()
//        viewModel.observeErrorPassword(lifecycleOwner) { error ->
//            binding.tilPassword.error = error
//        }
//
//        viewModel.observeError(lifecycleOwner) { error ->
//            binding.tvError.text = error
//            binding.tvError.visibility = View.VISIBLE
//        }
//
//        binding.btnSingIn.setOnClickListener {
//            viewModel.signInWithEmail()
//            binding.tvError.visibility = View.GONE
//        }
//
//        binding.btnSingUp.setOnClickListener {
//            viewModel.signUpWithEmail()
//            binding.tvError.visibility = View.GONE
//        }
//
//
//        binding.etPassword.doAfterTextChanged {
//            if (binding.etPassword.hasFocus()) {
//                viewModel.setPassword(it.toString())
//                binding.tilPassword.isErrorEnabled = false
//            }
//        }
    }

    private fun setupErrorTitle() {
        viewModel.observeError(lifecycleOwner) { error ->
            binding.tvError.text = error
            binding.tvError.visibility = View.VISIBLE
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
            binding.tvError.visibility = View.GONE
        }

    }

    private fun setupSighOut() {
        binding.btnSingUp.setOnClickListener {
            viewModel.signUpWithEmail()
            binding.tvError.visibility = View.GONE
        }

    }

}