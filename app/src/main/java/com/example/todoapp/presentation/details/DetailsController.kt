package com.example.todoapp.presentation.details

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentDetailsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


class DetailsController @AssistedInject constructor(
    @Assisted("taskDetailsFragment")  private val fragmentContext: DetailsFragment,
    @Assisted("taskDetailsFragmentBinding") private val binding: FragmentDetailsBinding,
    @Assisted("taskDetailsLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("taskDetailsViewModel") private val viewModel: DetailsViewModel,
    @Assisted("taskDetailsArgs") private val args: DetailsFragmentArgs,
    @Assisted("taskDetailsSpinnerAdapter")private val spinnerAdapter: ArrayAdapter<String>,
    @Assisted("taskDetailsDatePicker")private val datePicker: MaterialDatePicker<Long>,
//    private val longDateToString: LongDateToString
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("taskDetailsFragment") fragment: DetailsFragment,
            @Assisted("taskDetailsFragmentBinding") binding: FragmentDetailsBinding,
            @Assisted("taskDetailsLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("taskDetailsViewModel") viewModel: DetailsViewModel,
            @Assisted("taskDetailsArgs") args: DetailsFragmentArgs,
            @Assisted("taskDetailsSpinnerAdapter") spinnerAdapter: ArrayAdapter<String>,
            @Assisted("taskDetailsDatePicker") datePicker: MaterialDatePicker<Long>,
        ): DetailsController
    }

    fun setupViews() {
        setupToolbar()
        setupText()
        setupImportance()
//        setupDeadline()
//        setupDelete()
        setupCloseScreen()
    }

    private fun setupToolbar() {
        binding.ibCloseToolbar.setOnClickListener { viewModel.closeScreen() }
        binding.tvSaveToolbar.setOnClickListener {
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

//    private fun setupDeadline() {
//        viewModel.observeStateDeadline(lifecycleOwner) { state ->
//            state.apply(binding.tvDeadlineDate, binding.smDeadlineSwitch)
//            if (state is StateDeadlineUI.On) showDatePicker(datePicker)
//        }
//
//        binding.smDeadlineSwitch.setOnCheckedChangeListener { _, isChecked ->
//            viewModel.changeEnabled(isChecked)
//        }
//    }

//    private fun setupDelete() {
//        if (args.taskID == null) binding.llDeleteTaskButton.visibility = View.INVISIBLE
//
//        binding.llDeleteTaskButton.setOnClickListener {
//            viewModel.deleteTask()
//        }
//    }

    private fun setupCloseScreen() {
        viewModel.observeIsClose(lifecycleOwner) { close ->
            if (close) fragmentContext.findNavController().popBackStack()
        }
    }

    private fun showDatePicker(datePicker: MaterialDatePicker<Long>) {
        datePicker.addOnPositiveButtonClickListener { deadline ->
            if (deadline is Long) {
                viewModel.setDeadlineTask(deadline)
            }
        }
        datePicker.addOnNegativeButtonClickListener {
        }
        datePicker.addOnCancelListener {
        }
        datePicker.show(fragmentContext.parentFragmentManager, datePicker.toString())
    }

}
