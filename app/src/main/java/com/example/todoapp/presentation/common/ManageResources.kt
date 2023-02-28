package com.example.todoapp.presentation.common

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.todoapp.di.scope.AppScope
import javax.inject.Inject

@AppScope
class ManageResources @Inject constructor(
    private val context: Context
) {
   fun string(id: Int): String = context.getString(id)
   fun color(id: Int) = context.getColor(id)
}