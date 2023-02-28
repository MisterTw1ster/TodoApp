package com.example.todoapp.presentation.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.di.taskfragment.TasksFragmentComponent
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import com.example.todoapp.presentation.tasks.adapter.viewtype.TaskViewType
import com.example.todoapp.presentation.tasks.models.TaskUI
import javax.inject.Inject

class TasksFragment : Fragment() {

    lateinit var binding: FragmentTasksBinding

    private lateinit var tasksFragmentComponent: TasksFragmentComponent

    @Inject
    lateinit var tasksViewControllerFactory: TasksViewController.Factory
    private var tasksViewController: TasksViewController? = null

    @Inject
    lateinit var tasksViewModelFactory: TasksViewModelFactory.Factory
    private val viewModel: TasksViewModel by viewModels {
        tasksViewModelFactory.create(CommunicationTasks.Base())
    }

    private val tasksAdapter = TasksAdapter(
        listOf(
            TaskViewType(::showDetails, ::setIsDone),
        )
    )

    override fun onAttach(context: Context) {
        tasksFragmentComponent =
            context.appComponent.tasksFragmentComponent().create()
        tasksFragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasksViewController =
            tasksViewControllerFactory.create(
                this,
                binding,
                viewLifecycleOwner,
                viewModel,
                tasksAdapter
            )
        tasksViewController?.apply {
            setupViews()
        }

    }

    private fun showDetails(task: TaskUI) {
        val bundle =
            bundleOf("taskID" to task.id)
        findNavController().navigate(
            R.id.action_tasks_to_detailsFragment,
            bundle
        )
    }

    private fun setIsDone(task: TaskUI, value: Boolean) {
        viewModel.setIsDoneTask(task, value)
    }



}