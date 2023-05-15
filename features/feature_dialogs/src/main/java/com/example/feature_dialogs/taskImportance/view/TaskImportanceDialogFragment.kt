package com.example.feature_dialogs.taskImportance.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.ManageResources
import com.example.core.base.BaseBottomSheetFragment
import javax.inject.Inject
import com.example.feature_dialogs.databinding.TaskImportanceDialogFragmentBinding as Binding
import com.example.core.R
import com.example.feature_dialogs.taskImportance.viewModel.TaskImportanceComponentViewModel

class TaskImportanceDialogFragment: BaseBottomSheetFragment<Binding>() {

    @Inject
    lateinit var resources: ManageResources

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TaskImportanceComponentViewModel>()
            .component.inject(this)
        super.onAttach(context)
    }

    override fun initController() {
        super.initController()
        setupTitle()
        setupImportanceChoice()
    }

    private fun setupTitle() {
        binding.dialogTitle.title.text = DIALOG_TITLE
    }

    private fun setupImportanceChoice() {
        with(binding) {
            llLowGroup.setOnClickListener {
                setResult(resources.string(R.string.importance_low))
            }
            llBasicGroup.setOnClickListener {
                setResult(resources.string(R.string.importance_basic))
            }
            llImportantGroup.setOnClickListener {
                setResult(resources.string(R.string.importance_important))
            }
        }
    }

    private fun setResult(result: String) {
        setFragmentResult(IMPORTANCE_KEY_FR, bundleOf("bundleKey" to result))
        dismiss()
    }

    companion object {
        const val DIALOG_TITLE = "Важность"
        const val TAG = "DialogImportance"
        const val IMPORTANCE_KEY_FR = "importanceKey"
        fun newInstance() = TaskImportanceDialogFragment()
    }
}