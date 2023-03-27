//package com.example.todoapp.datasource.tasks.cloud.mappers
//
//import com.example.todoapp.core.testTaskCloud
//import com.example.todoapp.core.testTaskData
//import org.junit.Assert.assertEquals
//import org.junit.Test
//
//class TaskCloudToDataMapperTest {
//
//    @Test
//    fun `transform task cloud to task data return TaskData`() {
//        val taskCloud = testTaskCloud()
//
//        val expected = TaskCloudToDataMapper().transform(taskCloud)
//        val actual = testTaskData()
//
//        assertEquals(expected, actual)
//    }
//}