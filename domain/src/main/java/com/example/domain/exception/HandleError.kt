package com.example.domain.exception


interface HandleError<T> {
    fun handle(e: Exception): T
}
