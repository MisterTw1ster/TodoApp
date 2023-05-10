package com.example.core_task_data_source.cloud.mappers

import com.example.core_task_data_source.core.testTaskCloud
import com.example.core_task_data_source.core.testTaskData
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskDataToCacheMapperTest {

    @Test
    fun `transform task data with params to task cache return TaskCache`() {
        val taskData = testTaskData()

        val expected = TaskDataToCloudMapper().transform(taskData)
        val actual = testTaskCloud()

        assertEquals(expected, actual)
    }
}