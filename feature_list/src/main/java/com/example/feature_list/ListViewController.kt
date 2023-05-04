package com.example.feature_list

import androidx.core.view.GravityCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.feature_list.databinding.FragmentListBinding
import com.example.feature_list.databinding.HeaderNavigationDrawerBinding
import com.example.feature_list.rvlist.ItemTouchHelperCallback
import com.example.feature_list.rvlist.TaskUI
import com.example.feature_list.rvlist.TasksAdapter
import com.example.feature_list.rvsearchsuggestions.SuggestionsListAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ListViewController @AssistedInject constructor(
    @Assisted("listFragmentBinding") private val binding: FragmentListBinding,
    @Assisted("listLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("listViewModel") private val viewModel: ListViewModel,
    @Assisted("itemTouchHelperCallback") private val itemTouchHelperCallback: ItemTouchHelperCallback,
    private var adapterList: TasksAdapter,
    private var adapterListSuggestions: SuggestionsListAdapter,
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("listFragmentBinding") binding: FragmentListBinding,
            @Assisted("listLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("listViewModel") viewModel: ListViewModel,
            @Assisted("itemTouchHelperCallback") itemTouchHelperCallback: ItemTouchHelperCallback
        ): ListViewController

    }

    fun setupViews() {
        setupList()
        setupSearchBar()
        setupAddTask()
        setupSideMenu()
    }

    private fun setupList() {
        viewModel.observeTasks(lifecycleOwner) { state ->
            state.apply(binding.rvTasks, binding.tvEmptyList, binding.tvLoadingList)
        }
        adapterList.onClick = { task: TaskUI -> viewModel.showDetails(task.id) }
        adapterList.setIsDone =
            { task: TaskUI, value: Boolean -> viewModel.setIsDoneTask(task, value) }
        binding.rvTasks.adapter = adapterList

//        val callback = getItemTouchHelper()
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvTasks)

    }

//    private fun getItemTouchHelper(): ItemTouchHelper.SimpleCallback {
//        // Реакция на свайп и перемещение
//        return object : ItemTouchHelper.SimpleCallback(
//            0,
//            ItemTouchHelper.RIGHT
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapterList.currentList[viewHolder.adapterPosition]
////                repository!!.removeItemSimpleItemList(item)
//
//            }
//
//        }
//    }

    private fun setupSearchBar() {
        viewModel.observeListSuggestions(lifecycleOwner) { list ->
            adapterListSuggestions.submitList(list)
        }
        adapterListSuggestions.onClick = { item ->
            viewModel.searchTaskByText(item.id)
            binding.standardSearchBar.searchBar.text = item.id
            binding.standardSearchView.searchView.hide()
        }

        binding.standardSearchView.rvSearchSuggestions.adapter = adapterListSuggestions
        binding.standardSearchBar.searchBar.setNavigationIcon(R.drawable.ic_menu_24)
        binding.standardSearchBar.searchBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.standardSearchBar.searchBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.sort -> {
                    viewModel.showSorting()
                    true
                }
                R.id.filter -> {
                    viewModel.showFilters()
                    true
                }
                else -> TODO()
            }
        }

        binding.standardSearchView.searchView
            .editText
            .setOnEditorActionListener { _, _, _ ->
                binding.standardSearchBar.searchBar.text =
                    binding.standardSearchView.searchView.text
                viewModel.searchTaskByText(binding.standardSearchView.searchView.text.toString())
                binding.standardSearchView.searchView.hide()
                false
            }

    }

    private fun setupAddTask() {
        binding.fabAddTask.setOnClickListener {
            viewModel.showDetails()
        }
    }

    private fun setupSideMenu() {
        val viewHeader = binding.navView.getHeaderView(0)
        val bindingHeader = HeaderNavigationDrawerBinding.bind(viewHeader)

        viewModel.observeUserEmail(lifecycleOwner) { email ->
            bindingHeader.tvUserLogin.text = email
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

}
