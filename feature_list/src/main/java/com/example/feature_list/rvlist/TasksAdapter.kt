package com.example.feature_list.rvlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_list.databinding.ItemListBinding
import com.example.feature_list.di.ListScope
import javax.inject.Inject

@ListScope
class TasksAdapter @Inject constructor() : ListAdapter<TaskUI, TaskItemViewHolder>(TaskDiffCallback()) {

    // Устанавливаем при создании
    var onClick: ((TaskUI) -> Unit)? = null
    var setIsDone: ((TaskUI, value: Boolean) -> Unit)? = null

    // Как создавать view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return TaskItemViewHolder(binding, onClick, setIsDone)
    }

    // Как заполнять view
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

// Вынести в отдельный файл.
// ViewHolder хранит создаваемые view
class TaskItemViewHolder(
    private val binding: ItemListBinding,
    private val onClick: ((TaskUI) -> Unit)?,
    private val setIsDone: ((TaskUI, value: Boolean) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    // Вызываем из onBindViewHolder
    fun onBind(item: TaskUI) {
//        binding.twTextTask.text = item.text
//        binding.twTextTask.setOnClickListener { onClick?.invoke(item) }
        with(binding) {
            twTextTask.text = item.text
            cbIsDoneTask.isChecked = item.isDone
            ivImportanceTask.setBackgroundColor(item.color)
            tvDeadlineTask.text = item.deadlineText

            root.setOnClickListener {
                onClick?.invoke(item)
            }
            cbIsDoneTask.setOnCheckedChangeListener { _, isChecked ->
                setIsDone?.invoke(item, isChecked)
            }
        }
    }
}

// Вынести в отдельный файл.
class TaskDiffCallback : DiffUtil.ItemCallback<TaskUI>() {

    // Проверка равенства объектов по отдельным полям. Выполняется первой
    override fun areItemsTheSame(oldItem: TaskUI, newItem: TaskUI): Boolean {
        return oldItem.id == newItem.id
    }

    // Проверка равенства содержимого объектов.  Выполняется второй, если прошла первая
    // Если входящие позиции не data class, то проверять по полям
    override fun areContentsTheSame(oldItem: TaskUI, newItem: TaskUI): Boolean {
        return oldItem == newItem
    }

}
