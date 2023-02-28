package com.example.todoapp.presentation.common

import android.annotation.SuppressLint
import com.example.todoapp.di.scope.AppScope
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AppScope
class LongDateToString @Inject constructor() {

    @SuppressLint("SimpleDateFormat")
    fun ddMMMMyyyy(date: Long): String? {
        if (date == 0L) return null
        val format = SimpleDateFormat("dd MMMM yyyy")
        return format.format(Date(date))
    }
}