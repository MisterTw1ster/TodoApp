package com.example.todoapp.presentation.auth.models

import com.example.todoapp.presentation.common.ItemList

data class UserUI(
    val localId: String,
    val email: String
): ItemList
