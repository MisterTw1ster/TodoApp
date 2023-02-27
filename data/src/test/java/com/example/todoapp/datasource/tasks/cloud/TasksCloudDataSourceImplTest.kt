package com.example.todoapp.datasource.tasks.cloud

import com.example.todoapp.core.testTaskCloud
import com.example.todoapp.core.testTaskData
import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskCloudToDataMapper
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskDataToCloudMapper
import com.example.todoapp.exception.NoInternetConnectionException
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

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

        coEvery { api.fetchTasks() } answers {
            listOf(testTaskCloud(id = "1"), testTaskCloud(id = "2"))
        }
        coEvery { testCloudToDataMapper.transform(any()) } answers {
            testTaskData(id = firstArg<TaskCloud>().id.toLong())
        }

        val actual = testDataSource.fetchTasks()
        val expected = listOf(testTaskData(id = 1), testTaskData(id = 2))

        assertEquals(expected, actual)
    }

    @Test(expected = NoInternetConnectionException::class)
    fun `fetch tasks return TaskData list result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        coEvery { api.fetchTasks() } answers {
            throw NoInternetConnectionException()
        }

        testDataSource.fetchTasks()

    }

    @Test
    fun `add new task return TaskData result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        val taskData = testTaskData()

        coEvery { testDataToCloudMapper.transform(any()) } returns testTaskCloud()
        coEvery { api.addTask(any()) } returns testTaskCloud()
        coEvery { testCloudToDataMapper.transform(any()) } returns testTaskData()

        val expected = testTaskData()
        val actual = testDataSource.addTask(taskData)

        assertEquals(expected, actual)

    }

    @Test(expected = NoInternetConnectionException::class)
    fun `add new task return id result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        val taskData = testTaskData()

        coEvery { testDataToCloudMapper.transform(any()) } returns testTaskCloud()
        coEvery { api.addTask(any()) } answers {
            throw NoInternetConnectionException()
        }

       testDataSource.addTask(taskData)

    }

    @Test
    fun `edit task return id result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        val taskData = testTaskData()

        coEvery { testDataToCloudMapper.transform(any()) } returns testTaskCloud()
        coEvery { api.editTask(any(), any()) } returns testTaskCloud()
        coEvery { testCloudToDataMapper.transform(any()) } returns testTaskData()

        val expected = testTaskData()
        val actual = testDataSource.editTask(taskData)

        assertEquals(expected, actual)

    }

    @Test(expected = NoInternetConnectionException::class)
    fun `edit task return id result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )

        val taskData = testTaskData()

        coEvery { testDataToCloudMapper.transform(any()) } returns testTaskCloud()
        coEvery { api.editTask(any(), any()) } answers {
            throw NoInternetConnectionException()
        }

        testDataSource.editTask(taskData)

    }

    @Test
    fun `add tasks result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val tasksData = listOf(testTaskData(id = 1L), testTaskData(id = 2L))

        coEvery { testDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { api.addTasks(any()) } returns listOf(testTaskCloud(id = "1"), testTaskCloud(id = "2"))
        coEvery { testCloudToDataMapper.transform(any()) } answers {
            testTaskData(id = firstArg<TaskCloud>().id.toLong())
        }

        val expected = listOf(testTaskData(id = 1L), testTaskData(id = 2L))
        val actual = testDataSource.editTasks(tasksData)

        assertEquals(expected, actual)

    }

    @Test(expected = NoInternetConnectionException::class)
    fun `add tasks result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                api,
                testCloudToDataMapper,
                testDataToCloudMapper
            )
        val tasksData = listOf(testTaskData(id = 1L), testTaskData(id = 2L))

        coEvery { testDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { api.addTasks(any()) } answers {
            throw NoInternetConnectionException()
        }

        testDataSource.editTasks(tasksData)

    }

}