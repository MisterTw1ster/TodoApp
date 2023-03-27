//package com.example.todoapp.datasource.tasks.cloud.mappers
//
//import com.example.todoapp.core.testTaskCloud
//import com.example.todoapp.core.testTaskData
//import org.junit.Assert.assertEquals
//import org.junit.Test
//
//class TaskDataToCacheMapperTest {
//
//    @Test
//    fun `transform task data with params to task cache return TaskCache`() {
//        val taskData = testTaskData()
//
//        val expected = TaskDataToCloudMapper().transform(taskData)
//        val actual = testTaskCloud()
//
//        assertEquals(expected, actual)
//    }
//}