package com.example.feature_list.models

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_list.rvlist.TaskUI
import com.example.feature_list.rvlist.TasksAdapter

sealed class StateTasksUI {

    abstract fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: ProgressBar)

    class Success(private val data: List<TaskUI>): StateTasksUI() {
         override fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: ProgressBar) {
            val adapter = recyclerView.adapter
            (adapter as TasksAdapter).submitList(data)
             recyclerView.visibility = View.VISIBLE
             emptyText.visibility = View.GONE
             loadingText.visibility = View.GONE
        }
    }

    object Empty: StateTasksUI() {
        override fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: ProgressBar) {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
            loadingText.visibility = View.GONE
        }
    }

    object Loading: StateTasksUI() {
        override fun apply(recyclerView: RecyclerView, emptyText: TextView, loadingText: ProgressBar) {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.GONE
            loadingText.visibility = View.VISIBLE
       }
    }

}
