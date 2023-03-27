//package com.example.todoapp.mappers
//
//import com.example.todoapp.core.testTaskData
//import com.example.todoapp.core.testTaskDomain
//import org.junit.Assert.assertEquals
//import org.junit.Test
//
//class TaskDataToDomainMapperTest {
//
//    @Test
//    fun `transform task data to task domain return TaskDomain`() {
//        val taskData = testTaskData()
//
//        val expected = testTaskDomain()
//        val actual = TaskDataToDomainMapper().transform(taskData)
//
//        assertEquals(expected, actual)
//    }
//}