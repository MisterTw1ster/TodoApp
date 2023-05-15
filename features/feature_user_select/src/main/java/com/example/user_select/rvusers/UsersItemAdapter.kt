package com.example.user_select.rvusers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.user_select.databinding.ItemUserSelectBinding
import com.example.user_select.di.UserSelectScope
import javax.inject.Inject

@UserSelectScope
class UsersItemAdapter @Inject constructor() : ListAdapter<UserSelectUI, ItemViewHolder>(ItemDiffCallback()) {

    // Устанавливаем при создании
    var onClick: ((UserSelectUI) -> Unit)? = null

    // Как создавать view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSelectBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding, onClick)
    }

    // Как заполнять view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

// Вынести в отдельный файл.
// ViewHolder хранит создаваемые view
class ItemViewHolder(
    private val binding: ItemUserSelectBinding,
    private val onClick: ((UserSelectUI) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    // Вызываем из onBindViewHolder
    fun onBind(item: UserSelectUI) {
//        binding.twTextTask.text = item.text
//        binding.twTextTask.setOnClickListener { onClick?.invoke(item) }
        with(binding) {
            twUserName.text = item.email
            root.setOnClickListener { onClick?.let { it(item) } }
        }
    }
}

// Вынести в отдельный файл.
class ItemDiffCallback : DiffUtil.ItemCallback<UserSelectUI>() {

    // Проверка равенства объектов по отдельным полям. Выполняется первой
    override fun areItemsTheSame(oldItem: UserSelectUI, newItem: UserSelectUI): Boolean {
        return oldItem.localId == newItem.localId
    }

    // Проверка равенства содержимого объектов.  Выполняется второй, если прошла первая
    // Если входящие позиции не data class, то проверять по полям
    override fun areContentsTheSame(oldItem: UserSelectUI, newItem: UserSelectUI): Boolean {
        return oldItem == newItem
    }

}
