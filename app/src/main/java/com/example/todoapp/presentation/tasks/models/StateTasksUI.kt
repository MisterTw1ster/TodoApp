package com.example.todoapp.presentation.tasks.models

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper


sealed class StateTasksUI {

    abstract fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: TextView)

    class Success(private val data: List<TaskUI>): StateTasksUI() {
         override fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: TextView) {
            val adapter = recyclerView.adapter
            (adapter as TasksAdapter).submitList(data)
             recyclerView.visibility = View.VISIBLE
             emptyText.visibility = View.GONE
             loadingText.visibility = View.GONE
        }
    }

    object Empty: StateTasksUI() {
        override fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: TextView) {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
            loadingText.visibility = View.GONE
        }
    }

    object Loading: StateTasksUI() {
        override fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: TextView) {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.GONE
            loadingText.visibility = View.VISIBLE
       }
    }

}
