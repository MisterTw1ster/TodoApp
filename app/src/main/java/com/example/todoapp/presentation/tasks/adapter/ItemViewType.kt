package com.example.todoapp.presentation.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.todoapp.presentation.common.BaseViewHolder
import com.example.todoapp.presentation.common.ItemList

interface ItemViewType<V : ViewBinding, I : ItemList> {

    fun isRelativeItem(itemList: ItemList): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<V, I>

    fun getDiffUtil(): DiffUtil.ItemCallback<I>

}