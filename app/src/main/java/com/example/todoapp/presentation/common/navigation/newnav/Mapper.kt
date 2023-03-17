package com.example.todoapp.presentation.common.navigation.newnav

interface Mapper<R, S> {

    fun map(source: S): R

    interface Unit<S> : Mapper<kotlin.Unit, S>
}