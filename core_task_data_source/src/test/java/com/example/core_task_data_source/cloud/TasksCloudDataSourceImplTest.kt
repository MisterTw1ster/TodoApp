package com.example.core_task_data_source.cloud

import com.example.core_task_data_source.cloud.mappers.TaskCloudToDataMapper
import com.example.core_task_data_source.cloud.mappers.TaskDataToCloudMapper
import com.example.core_task_data_source.cloud.models.TaskCloud
import com.example.core_task_data_source.core.testTaskCloud
import com.example.core_task_data_source.core.testTaskData
import com.example.core_task_repository.models.TaskData
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TasksCloudDataSourceImplTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var api: TasksService

    @MockK
    lateinit var testDataToCloudMapper: TaskDataToCloudMapper

    @MockK
    lateinit var testCloudToDataMapper: TaskCloudToDataMapper

    @Test
    fun `fetch tasks return TaskData list result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val userId = "id1"

        coEvery { api.fetchTasks(any()) } answers {
            mapOf("1" to testTaskCloud(id = "1"), "2" to testTaskCloud(id = "2"))
        }
        coEvery { testCloudToDataMapper.transform(any(), any()) } answers {
            testTaskData(id = firstArg<TaskCloud>().id.toLong())
        }

        val expected = listOf(testTaskData(id = 1), testTaskData(id = 2))
        val actual = testDataSource.fetchTasks(userId)

        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun `fetch tasks return TaskData list result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val userId = "id1"

        coEvery { api.fetchTasks(any()) } answers {
            throw IllegalStateException()
        }

        testDataSource.fetchTasks(userId)

    }

    @Test
    fun `save task return TaskData result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        val taskData = testTaskData()

        coEvery { testDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { api.saveTask(any(), any()) } answers {
            testTaskCloud(id = firstArg<Map<String, TaskCloud>>().keys.first())
        }
        coEvery { testCloudToDataMapper.transform(any(), any()) } answers {
            testTaskData(id = firstArg<TaskCloud>().id.toLong())
        }

        val expected = testTaskData()
        val actual = testDataSource.saveTask(taskData)

        assertEquals(expected, actual)

    }

    @Test(expected = IllegalStateException::class)
    fun `save task return id result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        val taskData = testTaskData()

        coEvery { testDataToCloudMapper.transform(any()) } returns testTaskCloud()
        coEvery { api.saveTask(any(), any()) } answers {
            throw IllegalStateException()
        }

       testDataSource.saveTask(taskData)

    }

    @Test
    fun `delete task result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val id = 2L
        val tasksCloud = mutableListOf(testTaskCloud(id = "2"))
        val userId = "id1"

        coEvery { api.deleteTaskById(any(), any()) } answers {
            val element = tasksCloud.find { it.id == firstArg()  }
            tasksCloud.remove(element)
        }

        testDataSource.deleteTaskById(id, userId)
        val expected = emptyList<TaskCloud>()
        val actual = tasksCloud

        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun `delete task result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val id = 2L
        val userId = "id1"

        coEvery { api.deleteTaskById(any(), any()) } answers {
            throw IllegalStateException()
        }

        testDataSource.deleteTaskById(id, userId)

    }

    @Test
    fun `replace tasks result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val tasksCloud = mutableListOf(
            testTaskCloud(id = "1"),
            testTaskCloud(id = "2")
        )
        val newTasksData = mutableListOf(
            testTaskData(id = 3L),
            testTaskData(id = 4L)
        )
        val userId = "id1"

        coEvery { testDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { api.replaceTasks(any(), any()) } answers {
            tasksCloud.clear()
            tasksCloud.addAll(firstArg<Map<String, TaskCloud>>().values)
        }

        testDataSource.replaceTasks(newTasksData, userId)
        val expected = mutableListOf(
            testTaskCloud(id = "3"),
            testTaskCloud(id = "4")
        )
        val actual = tasksCloud

        assertEquals(expected, actual)

    }

    @Test(expected = IllegalStateException::class)
    fun `replace tasks result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val newTasksData = mutableListOf(
            testTaskData(id = 3L),
            testTaskData(id = 4L)
        )
        val userId = "id1"

        coEvery { testDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { api.replaceTasks(any(), any()) } answers {
            throw IllegalStateException()
        }

        testDataSource.replaceTasks(newTasksData, userId)

    }

}