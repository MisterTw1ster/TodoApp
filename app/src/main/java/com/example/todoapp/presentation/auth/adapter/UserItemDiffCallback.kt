package com.example.todoapp.presentation.auth.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.presentation.auth.models.UserUI
import javax.inject.Inject

@AppScope
class UserItemDiffCallback @Inject constructor() : DiffUtil.ItemCallback<UserUI>() {

    override fun areItemsTheSame(oldItemList: UserUI, newItemList: UserUI): Boolean {
        if (oldItemList::class != newItemList::class) return false
        return oldItemList.localId == newItemList.localId
    }

    override fun areContentsTheSame(oldItemList: UserUI, newItemList: UserUI): Boolean {
        if (oldItemList::class != newItemList::class) return false
        return oldItemList == newItemList
    }
}