package com.example.core

import android.annotation.SuppressLint
import dagger.Reusable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@Reusable
class DateLongToString @Inject constructor() {

    @SuppressLint("SimpleDateFormat")
    fun ddMMMMyyyy(date: Long): String? {
        if (date == 0L) return null
        val format = SimpleDateFormat("dd MMMM yyyy")
        return format.format(Date(date))
    }
}