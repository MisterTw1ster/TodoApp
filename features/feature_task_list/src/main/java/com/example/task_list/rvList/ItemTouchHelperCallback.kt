package com.example.task_list.rvList

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R.*
import com.example.task_list.viewModel.ListViewModel
import com.example.task_list.view.DeleteItemSnackbar
import com.example.task_list.view.DeleteItemSnackbar.Companion.setAction
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ItemTouchHelperCallback @AssistedInject constructor(
    @Assisted("viewModel") private val viewModel: ListViewModel,
    @Assisted("view") private val view: View,
    private val adapterList: TasksAdapter
): ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("viewModel") viewModel: ListViewModel,
            @Assisted("view") view: View,
        ): ItemTouchHelperCallback
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val item = adapterList.currentList[viewHolder.adapterPosition]
        viewModel.markForDeletionTask(item)
        DeleteItemSnackbar.make(view).apply {
            setAction {
                viewModel.uncheckToDeleteTaskUseCase(item)
            }
        }.show()

    }

}