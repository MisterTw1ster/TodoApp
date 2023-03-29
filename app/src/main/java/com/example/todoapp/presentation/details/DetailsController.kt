package com.example.todoapp.presentation.details

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentDetailsBinding
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class DetailsController @AssistedInject constructor(
    @Assisted("taskDetailsFragment")  private val fragment: DetailsFragment,
    @Assisted("taskDetailsFragmentBinding") private val binding: FragmentDetailsBinding,
    @Assisted("taskDetailsLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("taskDetailsViewModel") private val viewModel: DetailsViewModel,
//    @Assisted("taskDetailsArgs") private val args: DetailsFragmentArgs,
    @Assisted("taskDetailsArgs") private val args: Long,
    @Assisted("taskDetailsSpinnerAdapter")private val spinnerAdapter: ArrayAdapter<String>,
    @Assisted("taskDetailsDatePicker")private val datePicker: MaterialDatePicker<Long>,
//    private val navigation: Navigation
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("taskDetailsFragment") fragment: DetailsFragment,
            @Assisted("taskDetailsFragmentBinding") binding: FragmentDetailsBinding,
            @Assisted("taskDetailsLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("taskDetailsViewModel") viewModel: DetailsViewModel,
//            @Assisted("taskDetailsArgs") args: DetailsFragmentArgs,
            @Assisted("taskDetailsArgs") args: Long,
            @Assisted("taskDetailsSpinnerAdapter") spinnerAdapter: ArrayAdapter<String>,
            @Assisted("taskDetailsDatePicker") datePicker: MaterialDatePicker<Long>,
        ): DetailsController
    }

    fun setupViews() {
        setupModeScreenDetails()
        setupToolbar()
        setupText()
        setupImportance()
        setupDeadline()
        setupDelete()
        setupCloseScreen()
    }

    private fun setupModeScreenDetails() {
        viewModel.observeModeScreenDetails(lifecycleOwner) { mode ->
            mode.apply(binding)
        }
    }

    private fun setupToolbar() {
//        binding.ibCloseToolbar.setOnClickListener { viewModel.closeScreen() }
        binding.btnSaveTask.setOnClickListener {
            viewModel.saveTask()
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
        viewModel.observeImportance(lifecycleOwner) {
            binding.spinImportanceTask.setSelection(spinnerAdapter.getPosition(it))
        }
        binding.spinImportanceTask.adapter = spinnerAdapter
        binding.spinImportanceTask.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setImportanceTask(
                        binding.spinImportanceTask.adapter.getItem(position).toString()
                    )
                }
            }
    }

    private fun setupDeadline() {
        viewModel.observeDeadlineState(lifecycleOwner) { state ->
            state.apply(binding.tvDeadlineDate, binding.smDeadlineSwitch)
        }


        binding.smDeadlineSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) showDatePicker(datePicker) else viewModel.setDeadlineTask()
        }
    }

    private fun setupDelete() {
//        if (args == TaskUI.NEW_TASK_ID) binding.btnDeleteTask.visibility = View.INVISIBLE
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
