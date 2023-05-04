package com.example.core

import android.content.Context

class ManageResources (
    private val context: Context
) {
    fun string(id: Int): String = context.getString(id)
    fun color(id: Int) = context.getColor(id)
}