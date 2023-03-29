package com.example.todoapp.presentation.details.models

import android.view.View
import com.example.todoapp.databinding.FragmentDetailsBinding

sealed class ModeScreenDetails {
    abstract fun apply(binding: FragmentDetailsBinding)

    object New : ModeScreenDetails() {
        override fun apply(binding: FragmentDetailsBinding) {
            binding.btnDeleteTask.visibility = View.GONE
            binding.standardToolbar.tvTitle.text = "Новая задача"
        }
    }
    object Edit : ModeScreenDetails() {
        override fun apply(binding: FragmentDetailsBinding) {
            binding.btnDeleteTask.visibility = View.VISIBLE
            binding.standardToolbar.tvTitle.text = "Редактировать"
        }
    }
}

