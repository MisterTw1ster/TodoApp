package com.example.todoapp.presentation.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.todoapp.presentation.common.BaseViewHolder
import com.example.todoapp.presentation.common.ItemList

class TasksAdapter(
    private val itemListViewType: List<ItemViewType<*, *>>
) : ListAdapter<ItemList, BaseViewHolder<ViewBinding, ItemList>>(TaskDiffCallback(itemListViewType)) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, ItemList> {
        val inflater = LayoutInflater.from(parent.context)
        return itemListViewType.find { it.getLayoutId() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, ItemList> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, ItemList>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return itemListViewType.find { it.isRelativeItem(item) }
            ?.getLayoutId()
            ?: throw IllegalArgumentException("View type not found: $item")
    }

}