package com.example.feature_tasks_sorting

import androidx.lifecycle.LifecycleOwner
import com.example.feature_tasks_sorting.databinding.FragmentTasksSortingBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksSortingViewController @AssistedInject constructor(
    @Assisted("sortingFragment") private val fragment: TasksSortingFragment,
    @Assisted("sortingFragmentBinding") private val binding: FragmentTasksSortingBinding,
    @Assisted("sortingLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("sortingViewModel") private val viewModel: TasksSortingViewModel
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("sortingFragment") fragment: TasksSortingFragment,
            @Assisted("sortingFragmentBinding") binding: FragmentTasksSortingBinding,
            @Assisted("sortingLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("sortingViewModel") viewModel: TasksSortingViewModel
        ): TasksSortingViewController
    }

    fun setupViews() {
        setupSortMode()
        setupSaveSortMode()
        setupCloseScreen()
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
                R.id.created_at_desc_sort -> viewModel.setSortMode("created_at_desc")
                R.id.created_at_inc_sort -> viewModel.setSortMode("created_at_inc")
                R.id.change_at_desc_sort -> viewModel.setSortMode("change_at_desc")
                R.id.change_at_inc_sort -> viewModel.setSortMode("change_at_inc")
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
