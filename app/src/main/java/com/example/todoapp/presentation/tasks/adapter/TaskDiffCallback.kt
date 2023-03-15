package com.example.todoapp.presentation.tasks.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.presentation.common.ItemList

class TaskDiffCallback(
    private val itemListViewType: List<ItemViewType<*, *>>,
) : DiffUtil.ItemCallback<ItemList>() {

    override fun areItemsTheSame(oldItemList: ItemList, newItemList: ItemList): Boolean {
        if (oldItemList::class != newItemList::class) return false
        return getItemCallback(oldItemList).areItemsTheSame(oldItemList, newItemList)
    }

    override fun areContentsTheSame(oldItemList: ItemList, newItemList: ItemList): Boolean {
        if (oldItemList::class != newItemList::class) return false
        return getItemCallback(oldItemList).areContentsTheSame(oldItemList, newItemList)
    }

    private fun getItemCallback(
        itemList: ItemList
    ): DiffUtil.ItemCallback<ItemList> = itemListViewType.find { it.isRelativeItem(itemList) }
        ?.getDiffUtil()
        ?.let { it as DiffUtil.ItemCallback<ItemList> }
        ?: throw IllegalStateException("DiffUtil not found for $itemList")
}