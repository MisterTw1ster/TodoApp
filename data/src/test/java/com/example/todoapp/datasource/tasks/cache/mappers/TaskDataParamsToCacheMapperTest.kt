package com.example.todoapp.datasource.tasks.cache.mappers

import com.example.todoapp.core.testTaskCache
import com.example.todoapp.core.testTaskDataParams
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskDataParamsToCacheMapperTest {

    @Test
    fun `transform task data with params to task cache return TaskCache`() {
        val id = 1L
        val time = 0L
        val taskData = testTaskDataParams()

        val expected = TaskDataParamsToCacheMapper().transform(
            id = id , params = taskData, time, time
        )
        val actual = testTaskCache()

        assertEquals(expected, actual)
    }
}