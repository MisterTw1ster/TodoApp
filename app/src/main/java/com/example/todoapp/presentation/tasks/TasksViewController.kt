package com.example.todoapp.presentation.tasks

import androidx.core.view.GravityCompat
import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.databinding.ViewSideMenuHeaderBinding
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import com.example.todoapp.presentation.tasks.adapter.viewtype.TaskViewType
import com.example.todoapp.presentation.tasks.models.TaskUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksViewController @AssistedInject constructor(
    @Assisted("tasksFragmentBinding") private val binding: FragmentTasksBinding,
    @Assisted("tasksLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("tasksViewModel") private val viewModel: TasksViewModel
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("tasksFragmentBinding") binding: FragmentTasksBinding,
            @Assisted("tasksLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("tasksViewModel") viewModel: TasksViewModel
        ): TasksViewController
    }

    fun setupViews() {
        setupTasks()
        setupSideMenu()
        setupReports()
        setupAddTask()
        setupBottomMenu()

        //TODO delete after add fragment filter
//        viewModel.observeFilterCompleted(lifecycleOwner) { state ->
//            state.apply(binding.cbHideCompleted)
//        }
//        binding.cbHideCompleted.setOnCheckedChangeListener { _, isChecked ->
//            viewModel.saveSettingHideCompleted(isChecked)
//        }
    }

    private fun setupTasks() {
        val showDetails = { task: TaskUI -> viewModel.showDetails(task.id) }
        val setIsDone = { task: TaskUI, value: Boolean -> viewModel.setIsDoneTask(task, value) }
        binding.rvList.rvListAndTasks.apply {
            adapter = TasksAdapter(listOf(TaskViewType(showDetails, setIsDone)))
        }
        viewModel.observeTasks(lifecycleOwner) { state ->
            state.apply(
                binding.rvList.rvListAndTasks,
                binding.tvEmptyList,
                binding.tvLoadingList
            )

        }
    }

    private fun setupSideMenu() {
        val viewHeader = binding.navView.getHeaderView(0)
        val bindingHeader = ViewSideMenuHeaderBinding.bind(viewHeader)

        viewModel.observeUser(lifecycleOwner) { user ->
            bindingHeader.tvUserLogin.text = user.email
        }
        binding.ibMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_sign_out -> {
                    viewModel.signOut()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupReports() {
        viewModel.observeCntTasksImportantNotCompleted(lifecycleOwner) { cntTasks ->
            binding.twCountTaskImportantValue.text = cntTasks.toString()
        }

        viewModel.observeCntTasksNotCompleted(lifecycleOwner) { cntTasks ->
            binding.tvCountTaskValue.text = cntTasks.toString()
        }

        viewModel.observeCntTasksCompleted(lifecycleOwner) { cntTasks ->
            binding.twCountTaskCompletedValue.text = cntTasks.toString()
        }
    }

    private fun setupAddTask() {
        binding.fabAddTask.setOnClickListener {
            viewModel.showDetails()
        }
    }

    private fun setupBottomMenu() {
        binding.btmAppBarFab.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btm_search -> {
                    viewModel.showDetails()
                    true
                }
                R.id.btm_sort -> {
                    viewModel.showSorting()
                    true
                }
                R.id.btm_filter -> {
                    viewModel.showFilters()
                    true
                }
                else -> false
            }
        }
    }
}
