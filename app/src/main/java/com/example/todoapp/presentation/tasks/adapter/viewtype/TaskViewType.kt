package com.example.todoapp.presentation.tasks.adapter.viewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.presentation.common.BaseViewHolder
import com.example.todoapp.presentation.common.ItemList
import com.example.todoapp.presentation.tasks.adapter.ItemViewType
import com.example.todoapp.presentation.tasks.models.TaskUI

class TaskViewType(
    private val showDetails: (TaskUI) -> Unit,
    private val setIsDone: (TaskUI, value: Boolean) -> Unit
) : ItemViewType<ItemTaskBinding, TaskUI> {

    override fun isRelativeItem(itemList: ItemList) = itemList is TaskUI

    override fun getLayoutId() = LAYOUT_ID

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemTaskBinding, TaskUI> {
        val binding = ItemTaskBinding.inflate(layoutInflater, parent, false)
        return TaskViewHolder(binding, showDetails, setIsDone)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<TaskUI> {
        return diffUtil
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TaskUI>() {
        override fun areItemsTheSame(oldItem: TaskUI, newItem: TaskUI) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskUI, newItem: TaskUI) = oldItem == newItem
    }

    companion object {
        const val LAYOUT_ID = R.layout.item_task
    }

}

class TaskViewHolder(
    binding: ItemTaskBinding,
    private val showDetails: (TaskUI) -> Unit,
    private val setIsDone: (TaskUI, value: Boolean) -> Unit
) : BaseViewHolder<ItemTaskBinding, TaskUI>(binding) {

    override fun onBind(item: TaskUI) {
        with(binding) {
            twTextTask.text = item.text
            cbIsDoneTask.isChecked = item.isDone
            ivImportanceTask.setBackgroundColor(item.color)
            tvDeadlineTask.text = item.deadlineText

            root.setOnClickListener {
                showDetails(item)
            }
            cbIsDoneTask.setOnCheckedChangeListener { _, isChecked ->
                setIsDone(item, isChecked)
            }
        }
    }

}