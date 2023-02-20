package com.example.todoapp.datasource.tasks.cache.mappers

import com.example.todoapp.core.testTaskCache
import com.example.todoapp.core.testTaskData
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskDataToCacheMapperTest {

    @Test
    fun `transform task data to task cache return TaskCache`() {
        val taskData = testTaskData()

        val expected = TaskDataToCacheMapper().transform(taskData)
        val actual = testTaskCache()

        assertEquals(expected, actual)
    }
}