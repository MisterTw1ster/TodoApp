package com.example.details.models

import android.view.View
import com.example.details.databinding.FragmentTaskDetailsBinding

sealed class ModeScreenDetails {
    abstract fun apply(binding: FragmentTaskDetailsBinding)

    object New : ModeScreenDetails() {
        override fun apply(binding: FragmentTaskDetailsBinding) {
            binding.btnDeleteTask.visibility = View.GONE
            binding.standardToolbar.tvTitle.text = "Новая задача"
        }
    }
    object Edit : ModeScreenDetails() {
        override fun apply(binding: FragmentTaskDetailsBinding) {
            binding.btnDeleteTask.visibility = View.VISIBLE
            binding.standardToolbar.tvTitle.text = "Редактировать"
        }
    }
}

