package com.example.task_details.view

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.core.ManageResources
import com.example.core.R
import com.example.feature_task_details.databinding.FragmentDetailsBinding
import com.example.task_details.viewModel.DetailsViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TaskDetailsViewController @AssistedInject constructor(
    @Assisted("taskDetailsFragment")  private val fragment: TaskDetailsFragment,
    @Assisted("taskDetailsFragmentBinding") private val binding: FragmentDetailsBinding,
    @Assisted("taskDetailsLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("taskDetailsViewModel") private val viewModel: DetailsViewModel,
    private val resources: ManageResources
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("taskDetailsFragment") fragment: TaskDetailsFragment,
            @Assisted("taskDetailsFragmentBinding") binding: FragmentDetailsBinding,
            @Assisted("taskDetailsLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("taskDetailsViewModel") viewModel: DetailsViewModel
        ): TaskDetailsViewController
    }

    fun setupViews() {
        setupModeScreenDetails()
        setupIsDone()
        setupText()
        setupImportance()
        setupDeadline()
        setupDelete()
        setupSave()
        setupCloseScreen()
    }

    private fun setupModeScreenDetails() {
        fragment.setFragmentResultListener("importanceKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            result?.let { viewModel.setImportanceTask(it) }
        }
        viewModel.observeModeScreenDetails(lifecycleOwner) { mode ->
            mode.apply(binding)
        }
    }

    private fun setupSave() {
        binding.btnSaveFilters.setOnClickListener {
            viewModel.saveTask()
        }
    }

    private fun setupIsDone() {
        viewModel.observeIsDone(lifecycleOwner) { isDone ->
            binding.cbIsDoneTask.isChecked = isDone
        }
        binding.cbIsDoneTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setIsDone(isChecked)
        }
    }

    private fun setupText() {
        viewModel.observeText(lifecycleOwner) {
            if (!binding.etTextTask.hasFocus()) {
                binding.etTextTask.setText(it)
            }
        }
        binding.etTextTask.doAfterTextChanged {
            if (binding.etTextTask.hasFocus()) {
                viewModel.setTextTask(it.toString())
            }
        }

    }

    private fun setupImportance() {
        binding.ivImportanceDetails.setOnClickListener {
            viewModel.showImportanceModal()
        }
        viewModel.observeImportance(lifecycleOwner) {
            binding.ivImportanceDetails.setText(it)
        }
    }

    private fun setupDeadline() {
        val datePicker: MaterialDatePicker<Long> = MaterialDatePicker
            .Builder
            .datePicker()
            .setTitleText(resources.string(R.string.date_picker_title))
            .build()

        binding.llDeadlineDetails.setOnClickListener {
            showDatePicker(datePicker)
        }

        binding.ivDeadlineClearDetails.setOnClickListener {
            viewModel.setDeadlineTask(0L)
        }

        viewModel.observeDeadlineState(lifecycleOwner) { state ->
            state.apply(binding.tvDeadlineDetails, binding.ivDeadlineClearDetails)
        }

    }

    private fun setupDelete() {
        binding.btnDeleteTask.setOnClickListener {
            viewModel.deleteTask()
        }
    }

    private fun setupCloseScreen() {
        binding.standardToolbar.ibToolbarClose.setOnClickListener {
            viewModel.closeScreen()
        }
    }

    private fun showDatePicker(datePicker: MaterialDatePicker<Long>) {
        datePicker.addOnPositiveButtonClickListener { deadline ->
            viewModel.setDeadlineTask(deadline)
        }
        datePicker.addOnNegativeButtonClickListener {
        }
        datePicker.addOnCancelListener {
        }
        datePicker.show(fragment.parentFragmentManager, datePicker.toString())
    }

}
