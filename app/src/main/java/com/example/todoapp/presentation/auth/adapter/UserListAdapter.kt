package com.example.todoapp.presentation.auth.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todoapp.databinding.ItemUserBinding
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.presentation.auth.models.UserUI
import javax.inject.Inject

@AppScope
class UserListAdapter @Inject constructor(userItemDiffCallback: UserItemDiffCallback) :
    ListAdapter<UserUI, UserItemViewHolder>(userItemDiffCallback) {

    private var onItemClickListener: ((UserUI) -> Unit)? = null

    fun setOnItemClickListener(listener: (UserUI) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserItemViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}