package com.example.core_task_data_source.cache.mappers

import com.example.core_task_data_source.core.testTaskCache
import com.example.core_task_data_source.core.testTaskData
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskDataToCacheMapperTest {
    @Test
    fun `transform task data to task cache return TaskCache`() {
        val taskData = testTaskData()

        val expected = testTaskCache()
        val actual = TaskDataToCacheMapper().transform(taskData)

        assertEquals(expected, actual)
    }
}