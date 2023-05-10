package com.example.core_task_data_source.cloud.mappers

import com.example.core_task_data_source.core.testTaskCloud
import com.example.core_task_data_source.core.testTaskData
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskCloudToDataMapperTest {

    @Test
    fun `transform task cloud to task data return TaskData`() {
        val taskCloud = testTaskCloud()
        val userId = "id1"

        val expected = TaskCloudToDataMapper().transform(taskCloud, userId)
        val actual = testTaskData()

        assertEquals(expected, actual)
    }
}