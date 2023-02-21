package com.example.todoapp.datasource.tasks.cloud

import com.example.todoapp.core.testTaskCloud
import com.example.todoapp.core.testTaskData
import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskCloudToDataMapper
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskDataToCloudMapper
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
    lateinit var testService: TasksService

    @MockK
    lateinit var testTaskDataToCloudMapper: TaskDataToCloudMapper

    @MockK
    lateinit var testTaskCloudToDataMapper: TaskCloudToDataMapper

    @Test
    fun `fetch tasks return TaskData list result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                testService,
                testTaskCloudToDataMapper,
                testTaskDataToCloudMapper
            )

        coEvery { testService.fetchTasks() } answers {
            listOf(testTaskCloud(id = "1"), testTaskCloud(id = "2"))
        }
        coEvery { testTaskCloudToDataMapper.transform(any()) } answers {
            testTaskData(id = firstArg<TaskCloud>().id.toLong())
        }

        val actual = testDataSource.fetchTasks()
        val expected = listOf(testTaskData(id = 1), testTaskData(id = 2))

        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun `fetch tasks return TaskData list result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                testService,
                testTaskCloudToDataMapper,
                testTaskDataToCloudMapper
            )

        coEvery { testService.fetchTasks() } answers {
            listOf(testTaskCloud(id = "1"), testTaskCloud(id = "2"))
        }
        coEvery { testTaskCloudToDataMapper.transform(any()) } answers {
            testTaskData(id = firstArg<TaskCloud>().id.toLong())
        }

        testDataSource.fetchTasks()

    }

    @Test
    fun `add new task return id result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                testService,
                testTaskCloudToDataMapper,
                testTaskDataToCloudMapper
            )

        val taskData = testTaskData()
        val id = "1"

        coEvery { testTaskDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { testService.addTask(any()) } returns id

        val expected = 1
        val actual = testDataSource.addTask(taskData)

        assertEquals(expected, actual)

    }

    @Test(expected = IllegalStateException::class)
    fun `add new task return id result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                testService,
                testTaskCloudToDataMapper,
                testTaskDataToCloudMapper
            )

        val taskData = testTaskData()
        val id = "1"

        coEvery { testTaskDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { testService.addTask(any()) } returns id

        testDataSource.addTask(taskData)

    }

    @Test
    fun `edit task return id result success`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                testService,
                testTaskCloudToDataMapper,
                testTaskDataToCloudMapper
            )

        val taskData = testTaskData()
        val id = "1"

        coEvery { testTaskDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { testService.addTask(any()) } returns id

        val expected = 1
        val actual = testDataSource.addTask(taskData)

        assertEquals(expected, actual)

    }

    @Test(expected = IllegalStateException::class)
    fun `edit task return id result failed`() = runTest {
        val testDataSource =
            TasksCloudDataSourceImpl(
                testService,
                testTaskCloudToDataMapper,
                testTaskDataToCloudMapper
            )

        val taskData = testTaskData()
        val id = "1"

        coEvery { testTaskDataToCloudMapper.transform(any()) } answers {
            testTaskCloud(id = firstArg<TaskData>().id.toString())
        }
        coEvery { testService.addTask(any()) } returns id

        testDataSource.addTask(taskData)

    }

    @Test
    fun `add tasks result success`() = runTest {
//        val testDataSource =
//            TasksCloudDataSourceImpl(
//                testService,
//                testTaskCloudToDataMapper,
//                testTaskDataToCloudMapper
//            )
//        val tasksData = listOf(testTaskData(id = 1), testTaskData(id = 2))
//
//        coEvery { testTaskDataToCloudMapper.transform(any()) } answers {
//            testTaskCloud(id = firstArg<TaskCloud>().id)
//        }
//        coEvery { testService.addTasks(any()) } just runs

    }

    @Test(expected = IllegalStateException::class)
    fun `add tasks result failed`() = runTest {
//        val testDataSource =
//            TasksCloudDataSourceImpl(
//                testService,
//                testTaskCloudToDataMapper,
//                testTaskDataToCloudMapper
//            )
//        val tasksData = listOf(testTaskData(id = 1), testTaskData(id = 2))
//
//        coEvery { testTaskDataToCloudMapper.transform(any()) } answers {
//            testTaskCloud(id = firstArg<TaskData>().id.toString())
//        }
//        coEvery { testService.addTasks(any()) } just Runs

    }

}