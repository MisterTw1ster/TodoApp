package com.example.feature_dialogs.taskFilter.view

import androidx.lifecycle.LifecycleOwner
import com.example.core.ManageResources
import com.example.core.R
import com.example.feature_dialogs.databinding.TasksFilterDialogFragmentBinding
import com.example.feature_dialogs.taskFilter.viewModel.TaskFilterViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TaskFilterViewController @AssistedInject constructor(
    @Assisted("filtersFragment") private val fragment: TaskFilterDialogFragment,
    @Assisted("filtersFragmentBinding") private val binding: TasksFilterDialogFragmentBinding,
    @Assisted("filtersLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("filtersViewModel") private val viewModel: TaskFilterViewModel,
    private val resources: ManageResources
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("filtersFragment") fragment: TaskFilterDialogFragment,
            @Assisted("filtersFragmentBinding") binding: TasksFilterDialogFragmentBinding,
            @Assisted("filtersLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("filtersViewModel") viewModel: TaskFilterViewModel
        ): TaskFilterViewController
    }

    fun setupViews() {
        setupTitle()
        setupHideCompleted()
        setupSaveFilters()
        setupCloseScreen()
    }

    private fun setupTitle() {
        binding.dialogTitle.title.text = TaskFilterDialogFragment.DIALOG_TITLE
    }

    private fun setupHideCompleted() {
        viewModel.observeHideCompleted(lifecycleOwner) { hide ->
            val text = if (hide) {
                resources.string(R.string.title_hide_completed_false)
            } else {
                resources.string(R.string.title_hide_completed_true)
            }
            binding.ifvHideCompletedFilter.setText(text)
        }
        binding.ifvHideCompletedFilter.setOnClickListener {
            viewModel.setHideCompleted()
        }
    }

    private fun setupSaveFilters() {
        binding.btnSaveFilters.setOnClickListener {
            viewModel.saveFilters()
        }
    }

    private fun setupCloseScreen() {
        viewModel.observeCloseScreen(lifecycleOwner) { close ->
            if (close) fragment.dismiss()
        }
    }
}
