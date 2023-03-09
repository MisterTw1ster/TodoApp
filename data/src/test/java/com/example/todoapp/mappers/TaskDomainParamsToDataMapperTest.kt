package com.example.todoapp.mappers

import com.example.todoapp.core.testTaskData
import com.example.todoapp.core.testTaskDomainParams
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskDomainParamsToDataMapperTest {

    @Test
    fun `transform task domain params to task data return TaskData`() {
        val taskDomainParams = testTaskDomainParams()
        val createdAt = 1L
        val changedAt = 1L


        val expected = testTaskData(createdAt = createdAt, changedAt = changedAt)
        val actual = TaskDomainParamsToDataMapper().transform(taskDomainParams, createdAt, changedAt)

        assertEquals(expected, actual)
    }
}