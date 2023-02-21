package com.example.todoapp.datasource.tasks.cache.mappers

import com.example.todoapp.core.testTaskCache
import com.example.todoapp.core.testTaskData
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskCacheToDataMapperTest {

    @Test
    fun `transform task cache to task data return TaskData`() {
        val taskCache = testTaskCache()

        val expected = TaskCacheToDataMapper().transform(taskCache)
        val actual = testTaskData()

        assertEquals(expected, actual)
    }
}