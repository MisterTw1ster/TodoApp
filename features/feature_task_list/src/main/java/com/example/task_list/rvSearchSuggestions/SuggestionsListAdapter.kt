package com.example.task_list.rvSearchSuggestions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task_list.di.ListScope
import com.example.feature_task_list.databinding.ItemSearchSuggestionsBinding
import javax.inject.Inject

@ListScope
class SuggestionsListAdapter @Inject constructor() : ListAdapter<SuggestionsItem, SuggestionsItemViewHolder>(
    SuggestionsItemDiffCallback()
) {

    // Устанавливаем при создании
    var onClick: ((SuggestionsItem) -> Unit)? = null

    // Как создавать view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionsItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchSuggestionsBinding.inflate(layoutInflater, parent, false)
        return SuggestionsItemViewHolder(binding, onClick)
    }

    // Как заполнять view
    override fun onBindViewHolder(holder: SuggestionsItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

// Вынести в отдельный файл.
// ViewHolder хранит создаваемые view
class SuggestionsItemViewHolder(
    private val binding: ItemSearchSuggestionsBinding,
    private val onClick: ((SuggestionsItem) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    // Вызываем из onBindViewHolder
    fun onBind(item: SuggestionsItem) {
        binding.twText.text = item.id
        binding.twText.setOnClickListener { onClick?.invoke(item) }
    }
}

// Вынести в отдельный файл.
class SuggestionsItemDiffCallback : DiffUtil.ItemCallback<SuggestionsItem>() {

    // Проверка равенства объектов по отдельным полям. Выполняется первой
    override fun areItemsTheSame(oldItem: SuggestionsItem, newItem: SuggestionsItem): Boolean {
        return oldItem.id == newItem.id
    }

    // Проверка равенства содержимого объектов.  Выполняется второй, если прошла первая
    // Если входящие позиции не data class, то проверять по полям
    override fun areContentsTheSame(oldItem: SuggestionsItem, newItem: SuggestionsItem): Boolean {
        return oldItem == newItem
    }

}
