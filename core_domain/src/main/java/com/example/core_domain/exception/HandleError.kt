package com.example.core_domain.exception


interface HandleError<T> {
    fun handle(e: Exception): T
}
