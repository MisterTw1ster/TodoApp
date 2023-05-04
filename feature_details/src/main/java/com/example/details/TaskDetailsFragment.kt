package com.example.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.details.databinding.FragmentTaskDetailsBinding
import javax.inject.Inject

class TaskDetailsFragment: Fragment() {

    @Inject
    lateinit var viewControllerFactory: TaskDetailsViewController.Factory
    private var viewController: TaskDetailsViewController? = null

    @Inject
    lateinit var viewModelFactory: TaskDetailsViewModelFactory.Factory
    private val viewModel: DetailsViewModel by viewModels {
        viewModelFactory.create(taskId, userId)
    }

    private var binding: FragmentTaskDetailsBinding? = null
    private val taskId: Long by lazy { arguments?.getLong(ID_TASK)!! }
    private val userId: String by lazy { arguments?.getString(ID_USER)!! }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TaskDetailsComponentViewModel>()
            .taskDetailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        viewController =
            viewControllerFactory.create(
                this,
                binding!!,
                viewLifecycleOwner,
                viewModel
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewController = null
        binding = null
    }

    companion object {

        private const val ID_TASK = "task_id"
        private const val ID_USER = "user_id"

        fun newInstance(userId: String, taskId: Long) =
            TaskDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID_TASK, taskId)
                    putString(ID_USER, userId)
                }
            }
    }
}