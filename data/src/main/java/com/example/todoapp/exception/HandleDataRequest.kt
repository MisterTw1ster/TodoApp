//package com.example.todoapp.exception
//
//import com.example.todoapp.models.TaskDomain
//import javax.inject.Inject
//
//class HandleDataRequest @Inject constructor(
//    private val handleError: HandleError<Exception>
//) {
//    suspend fun handle(block: suspend () -> TaskDomain): TaskDomain = try {
//        block.invoke()
//    } catch (e: Exception) {
//        throw handleError.handle(e)
//    }
//}