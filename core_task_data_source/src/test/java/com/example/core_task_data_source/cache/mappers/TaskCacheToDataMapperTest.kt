package com.example.core_task_data_source.cache.mappers

import com.example.core_task_data_source.core.testTaskCache
import com.example.core_task_data_source.core.testTaskData
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskCacheToDataMapperTest {

    @Test
    fun `transform task cache to task data return TaskData`() {
        val taskCache = testTaskCache()

        val expected = testTaskData()
        val actual = TaskCacheToDataMapper().transform(taskCache)

        assertEquals(expected, actual)
    }
}