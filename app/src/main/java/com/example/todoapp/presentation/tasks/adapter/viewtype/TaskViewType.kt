package com.example.todoapp.presentation.tasks.adapter.viewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.presentation.tasks.adapter.BaseViewHolder
import com.example.todoapp.presentation.tasks.adapter.Item
import com.example.todoapp.presentation.tasks.adapter.ItemViewType

class TaskViewType(
    private val showDetails: (TaskUI) -> Unit,
    private val setIsDone: (TaskUI, value: Boolean) -> Unit
) : ItemViewType<ItemTaskBinding, TaskUI> {

    override fun isRelativeItem(item: Item) = item is TaskUI

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

        override fun areContentsTheSame(oldItem: TaskUI, newItem: TaskUI) = oldItem.id == newItem.id
                && oldItem.text == newItem.text

    }

    companion object {
        const val MAX_POOL_SIZE = 20
        const val LAYOUT_ID = R.layout.item_task
    }

}

class TaskViewHolder(
    binding: ItemTaskBinding,
    private val showDetails: (TaskUI) -> Unit,
    private val setIsDone: (TaskUI, value: Boolean) -> Unit
) : BaseViewHolder<ItemTaskBinding, TaskUI>(binding) {

    override fun onBind(
        item: TaskUI
    ) {
        with(binding) {
            twTextTask.text = item.text
//            cbIsDoneTask.isChecked = item.isDone
//            cbIsFavorite.isChecked = item.isFavorite
//
//            when (item.importance) {
//                "low" -> binding.ivImportanceTask.setBackgroundColor(Color.parseColor("#34C759"))
//                "important" -> binding.ivImportanceTask.setBackgroundColor(Color.parseColor("#FF3B30"))
//                else -> binding.ivImportanceTask.setBackgroundColor(Color.parseColor("#FFFFFF"))
//            }
//            when (item.deadline) {
//               0L -> binding.tvDeadlineTask.visibility = View.GONE
//               else -> {
//                   binding.tvDeadlineTask.text = item.deadline.toString()
//                   binding.tvDeadlineTask.visibility = View.VISIBLE
//                   binding.tvDeadlineTask.visibility = View.VISIBLE
//               }
//            }
//            root.setOnClickListener {
//                showDetails(item)
//            }
//            cbIsDoneTask.setOnCheckedChangeListener { _, isChecked ->
//                setIsDone(item, isChecked)
//            }
//            cbIsFavorite.setOnCheckedChangeListener { _, _ ->
//                setIsFavorite(item)
//            }
        }
    }

}