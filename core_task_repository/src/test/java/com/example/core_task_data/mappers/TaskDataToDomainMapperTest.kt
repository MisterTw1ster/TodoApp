package com.example.core_task_data.mappers

import com.example.core_task_data.core.testTaskData
import com.example.core_task_data.core.testTaskDomain
import com.example.core_task_repository.mappers.TaskDataToDomainMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskDataToDomainMapperTest {

    @Test
    fun `transform task data to task domain return TaskDomain`() {
        val taskData = testTaskData()

        val expected = testTaskDomain()
        val actual = TaskDataToDomainMapper().transform(taskData)

        assertEquals(expected, actual)
    }
}