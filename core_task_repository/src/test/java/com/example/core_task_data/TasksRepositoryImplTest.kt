package com.example.core_task_data

import com.example.domain.exception.tasks.NoInternetConnectionException
import com.example.core_task_data.core.testTaskData
import com.example.core_task_data.core.testTaskDomain
import com.example.core_task_data.core.testTaskDomainParams
import com.example.core_task_repository.TasksCacheDataSource
import com.example.core_task_repository.TasksCloudDataSource
import com.example.core_task_repository.TasksRepositoryImpl
import com.example.core_task_repository.exception.TasksHandleDataRequest
import com.example.core_task_repository.mappers.TaskDataToDomainMapper
import com.example.core_task_repository.mappers.TaskDomainParamsToDataMapper
import com.example.core_task_repository.models.TaskData
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TasksRepositoryImplTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var testCacheDataSource: TasksCacheDataSource
    @MockK
    lateinit var testCloudDataSource: TasksCloudDataSource
    @MockK
    lateinit var testDataToDomainMapper: TaskDataToDomainMapper
    @MockK
    lateinit var testDomainParamsToDataMapper: TaskDomainParamsToDataMapper
    @MockK
    lateinit var testHandleDataRequest: TasksHandleDataRequest

    @Test
    fun `observe tasks return flow TaskDomain list`() = runTest {
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
            )
        val userId = "id1"

        coEvery { testCacheDataSource.observeTasks() } returns flow {
            emit(listOf(testTaskData(id = 1), testTaskData(id = 2)))
            emit(listOf(testTaskData(id = 1), testTaskData(id = 3)))
        }

        coEvery { testDataToDomainMapper.transform(any()) } answers {
            testTaskDomain(id = firstArg<TaskData>().id)
        }

        val tasks = testRepository.observeTask(userId).toList()
        val expectedFirst = listOf(testTaskDomain(id = 1), testTaskDomain(id = 2))
        val actualFirst = tasks[0]
        val expectedSecond = listOf(testTaskDomain(id = 1), testTaskDomain(id = 3))
        val actualSecond = tasks[1]

        assertEquals(expectedFirst, actualFirst)
        assertEquals(expectedSecond, actualSecond)
    }

    @Test
    fun `get task by id return TaskDomain`() = runTest {
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
            )
        val id = 2L

        coEvery { testCacheDataSource.getTaskById(any()) } answers {
            testTaskData (id = firstArg())
        }
        coEvery { testDataToDomainMapper.transform(any()) } answers {
            testTaskDomain(id = firstArg<TaskData>().id)
        }

        val expected = testTaskDomain(id = id)
        val actual = testRepository.getTaskById(id)

        assertEquals(expected, actual)
    }

    @Test
    fun `add new task with params result success`() = runTest {
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
            )
        val time = 100L
        val taskDomainParams = testTaskDomainParams(id = 0L)

        coEvery { testDomainParamsToDataMapper.transform(any(), any(), any()) } answers {
            testTaskData(
                id = time,
                createdAt = secondArg(),
                changedAt = thirdArg()
            )
        }
        coEvery { testCloudDataSource.saveTask(any()) } answers {
            firstArg()
        }
        coEvery { testCacheDataSource.addTask(any()) } answers {
            firstArg()
        }
        coEvery { testDataToDomainMapper.transform(any()) } answers {
            with (firstArg<TaskData>()) {
                testTaskDomain(id = id, createdAt = createdAt, changedAt = changedAt)
            }
        }
        coEvery { testHandleDataRequest.handle(any(), any()) } answers {
            testTaskDomain(id = time, createdAt = time, changedAt = time)
        }

        val expected = testTaskDomain(id = time, createdAt = time, changedAt = time)
        val actual = testRepository.addTask(taskDomainParams)

        assertEquals(expected, actual)

    }

    @Test(expected = NoInternetConnectionException::class)
    fun `add new task with params result failed`() = runTest {
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
            )
        val time = System.currentTimeMillis()
        val taskDomainParams = testTaskDomainParams(id = 0L)

        coEvery { testDomainParamsToDataMapper.transform(any(), any(), any()) } answers {
            testTaskData(
                id = time,
                createdAt = secondArg(),
                changedAt = thirdArg()
            )
        }
        coEvery { testHandleDataRequest.handle(any(), any()) } answers {
            throw NoInternetConnectionException()
        }

        testRepository.addTask(taskDomainParams)

    }

    @Test
    fun `edit task with params result success`() = runTest {
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
            )
        val newCreatedAt = 100L
        val newChangedAt = 200L
        val newText = "new text"
        val taskDomainParams = testTaskDomainParams(text = newText)

        coEvery { testCacheDataSource.getTaskById(any()) } answers {
            testTaskData(id = newCreatedAt, text = "old text")
        }
        coEvery { testDataToDomainMapper.transform(any()) } answers {
            with (firstArg<TaskData>()) {
                testTaskDomain(text = text, createdAt = createdAt, changedAt = changedAt)
            }
        }
        coEvery { testDomainParamsToDataMapper.transform(any(), any(), any()) } answers {
            testTaskData(id = newCreatedAt, text = newText, createdAt = newCreatedAt, changedAt = newChangedAt)
        }
        coEvery { testCloudDataSource.saveTask(any()) } answers {
            firstArg()
        }
        coEvery { testCacheDataSource.editTask(any()) } answers {
            firstArg()
        }
        coEvery { testHandleDataRequest.handle(any(), any()) } answers {
            testTaskDomain(id = newCreatedAt, text = newText, createdAt = newCreatedAt, changedAt = newChangedAt)
        }

        val expected = testTaskDomain(id = newCreatedAt, text = newText, createdAt = newCreatedAt, changedAt = newChangedAt)
        val actual = testRepository.editTask(taskDomainParams)

        assertEquals(expected, actual)
    }

    @Test(expected = NoInternetConnectionException::class)
    fun `edit task with params result failed`() = runTest {
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
            )
        val newCreatedAt = 100L
        val newChangedAt = 200L
        val newText = "new text"
        val taskDomainParams = testTaskDomainParams(text = newText)

        coEvery { testCacheDataSource.getTaskById(any()) } answers {
            testTaskData(id = newCreatedAt, text = "old text")
        }
        coEvery { testDataToDomainMapper.transform(any()) } answers {
            with (firstArg<TaskData>()) {
                testTaskDomain(text = text, createdAt = createdAt, changedAt = changedAt)
            }
        }
        coEvery { testDomainParamsToDataMapper.transform(any(), any(), any()) } answers {
            testTaskData(id = newCreatedAt, text = newText, createdAt = newCreatedAt, changedAt = newChangedAt)
        }
        coEvery { testCloudDataSource.saveTask(any()) } answers {
            firstArg()
        }
        coEvery { testCacheDataSource.editTask(any()) } answers {
            firstArg()
        }
        coEvery { testHandleDataRequest.handle(any(), any()) } answers {
            throw NoInternetConnectionException()
        }

        testRepository.editTask(taskDomainParams)

    }
}