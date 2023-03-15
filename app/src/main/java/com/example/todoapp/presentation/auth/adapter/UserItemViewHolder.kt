package com.example.todoapp.presentation.auth.adapter

import com.example.todoapp.databinding.ItemUserBinding
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.common.BaseViewHolder

class UserItemViewHolder(
    binding: ItemUserBinding,
    private val onItemClickListener: ((UserUI) -> Unit)?
): BaseViewHolder<ItemUserBinding, UserUI>(binding) {
    override fun onBind(item: UserUI) {
        with(binding) {
            twUserLabel.text = item.email.first().toString()
            twUserName.text = item.email
            root.setOnClickListener { onItemClickListener?.let { it(item) } }
        }
    }
}