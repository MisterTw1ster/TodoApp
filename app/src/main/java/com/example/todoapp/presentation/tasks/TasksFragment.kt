package com.example.todoapp.presentation.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoapp.App
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentTasks2Binding
import com.example.todoapp.di.taskfragment.TasksFragmentComponent
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import com.example.todoapp.presentation.tasks.adapter.viewtype.TaskViewType
import com.example.todoapp.presentation.tasks.models.TaskUI
import javax.inject.Inject

class TasksFragment : Fragment() {

    private lateinit var tasksFragmentComponent: TasksFragmentComponent

    private val userId: String by lazy { arguments?.getString(ID_USER)!! }

    @Inject
    lateinit var tasksViewControllerFactory: TasksViewController.Factory
    private var tasksViewController: TasksViewController? = null


    @Inject
    lateinit var tasksViewModelFactory: TasksViewModelFactory.Factory
    private val viewModel: TasksViewModel by viewModels {
        tasksViewModelFactory.create(
//            args.userId,
            userId,
            CommunicationTasks.Base(),
            (requireActivity().applicationContext as App).provideNavigationCommunication()
        )
    }

    private var binding: FragmentTasks2Binding? = null

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
        binding = FragmentTasks2Binding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasksViewController =
            tasksViewControllerFactory.create(
                this,
                binding!!,
                viewLifecycleOwner,
                viewModel,
                tasksAdapter
            )
        tasksViewController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tasksViewController = null
        binding = null
    }

    private fun showDetails(task: TaskUI) {
//        navigation.editDetailsFragment(this, task.id)
        viewModel.showDetails(task.id)
    }

    private fun setIsDone(task: TaskUI, value: Boolean) {
        viewModel.setIsDoneTask(task, value)
    }


    companion object {

        private const val ID_USER = "user_id"

        @JvmStatic
        fun newInstance(userId: String) =
            TasksFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_USER, userId)
                }
            }
    }
}