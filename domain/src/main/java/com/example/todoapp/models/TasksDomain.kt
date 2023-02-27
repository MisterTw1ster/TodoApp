package com.example.todoapp.models

sealed class TasksDomain {

    data class Success(private val list: List<TaskDomain> = emptyList()) : TasksDomain() {
//        fun <T> map(mapper: Mapper<T>): T = mapper.map(list)
        fun map(): List<TaskDomain> = emptyList()
    }

    object Empty : TasksDomain() {
        //        fun map(mapper: Mapper<T>): T = mapper.map(message)
        fun map(): String = "mapper.map(message)"
    }
}