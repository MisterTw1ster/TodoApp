package com.example.feature_dialogs.taskSorting.view

import androidx.lifecycle.LifecycleOwner
import com.example.feature_dialogs.R
import com.example.feature_dialogs.databinding.TaskSortingDialogFragmentBinding
import com.example.feature_dialogs.taskSorting.viewModel.TaskSortingViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TaskSortingViewController @AssistedInject constructor(
    @Assisted("sortingFragment") private val fragment: TaskSortingDialogFragment,
    @Assisted("sortingFragmentBinding") private val binding: TaskSortingDialogFragmentBinding,
    @Assisted("sortingLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("sortingViewModel") private val viewModel: TaskSortingViewModel
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("sortingFragment") fragment: TaskSortingDialogFragment,
            @Assisted("sortingFragmentBinding") binding: TaskSortingDialogFragmentBinding,
            @Assisted("sortingLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("sortingViewModel") viewModel: TaskSortingViewModel
        ): TaskSortingViewController
    }

    fun setupViews() {
        setupTitle()
        setupSortMode()
        setupSaveSortMode()
        setupCloseScreen()
    }

    private fun setupTitle() {
        binding.dialogTitle.title.text = TaskSortingDialogFragment.DIALOG_TITLE
    }

    private fun setupSortMode() {
        viewModel.observeSortMode(lifecycleOwner) { sortMode ->
            when (sortMode) {
                "created_at_desc" -> binding.createdAtDescSort.isChecked = true
                "created_at_inc" -> binding.createdAtIncSort.isChecked = true
                "change_at_desc" -> binding.changeAtDescSort.isChecked = true
                "change_at_inc" -> binding.changeAtIncSort.isChecked = true
                else -> binding.createdAtDescSort.isChecked = true
            }
        }

        binding.sortingRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.createdAtDescSort -> viewModel.setSortMode("created_at_desc")
                R.id.createdAtIncSort -> viewModel.setSortMode("created_at_inc")
                R.id.changeAtDescSort -> viewModel.setSortMode("change_at_desc")
                R.id.changeAtIncSort -> viewModel.setSortMode("change_at_inc")
                else -> viewModel.setSortMode("created_at_desc")
            }
        }
    }

    private fun setupSaveSortMode() {
        binding.btnSaveSorting.setOnClickListener {
            viewModel.saveSortMode()
        }
    }

    private fun setupCloseScreen() {
        viewModel.observeCloseScreen(lifecycleOwner) { close ->
            if (close) fragment.dismiss()
        }
    }
}
