package com.example.todoapp.repository

import com.example.todoapp.core.testTaskData
import com.example.todoapp.core.testTaskDomain
import com.example.todoapp.core.testTaskDomainParams
import com.example.todoapp.exception.HandleDataRequest
import com.example.todoapp.exception.HandleError
import com.example.todoapp.mappers.TaskDataToDomainMapper
import com.example.todoapp.mappers.TaskDomainParamsToDataMapper
import com.example.todoapp.models.TaskData
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.repository.tasks.TasksCacheDataSource
import com.example.todoapp.repository.tasks.TasksCloudDataSource
import com.example.todoapp.repository.tasks.TasksRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

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
    lateinit var testHandleError: HandleError<Exception>
    @MockK
    lateinit var testHandleDataRequest: HandleDataRequest

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

        coEvery { testCacheDataSource.observeTasks() } returns flow {
            emit(listOf(testTaskData(id = 1), testTaskData(id = 2)))
            emit(listOf(testTaskData(id = 1), testTaskData(id = 3)))
        }

        coEvery { testDataToDomainMapper.transform(any()) } answers {
            testTaskDomain(id = firstArg<TaskData>().id)
        }

        val tasks = testRepository.observeTasks().toList()
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
//        val handleDataRequest = HandleDataRequest(testHandleError)
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
//                handleDataRequest
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
//            firstArg<()-> TaskDomain>().invoke()
            testTaskDomain(id = time, createdAt = time, changedAt = time)
        }

        val expected = testTaskDomain(id = time, createdAt = time, changedAt = time)
        val actual = testRepository.addTask(taskDomainParams)

        assertEquals(expected, actual)

    }

    @Test
    fun `add new task with params result failed`() = runTest {
//        val handleDataRequest = HandleDataRequest(testHandleError)
//        val testRepository =
//            TasksRepositoryImpl(
//                testCacheDataSource,
//                testCloudDataSource,
//                testDataToDomainMapper,
//                testDomainParamsToDataMapper,
//                handleDataRequest
//            )
//        val time = System.currentTimeMillis()
//        val taskDomainParams = testTaskDomainParams(id = 0L)
//        val newId = 2L
//
//        coEvery { testDomainParamsToDataMapper.transform(any(), time, time) } answers {
//            testTaskData(id = firstArg<TaskDomainParams>().id, createdAt = time, changedAt = time)
//        }
//        coEvery { testCacheDataSource.addTask(any()) } answers {
//            firstArg<TaskData>().copy(id = newId)
//        }
//        coEvery { testCloudDataSource.addTask(any()) } answers {
//            throw NoInternetConnectionException()
//        }
//
//        val expected = "No internet connection"
//        val actual = testRepository.addTask(taskDomainParams)
//
//        assertEquals(expected, actual)
        //        val handleDataRequest = HandleDataRequest(testHandleError)

    }

    @Test
    fun `edit task with params result success`() = runTest {
//        val handleDataRequest = HandleDataRequest(testHandleError)
        val testRepository =
            TasksRepositoryImpl(
                testCacheDataSource,
                testCloudDataSource,
                testDataToDomainMapper,
                testDomainParamsToDataMapper,
                testHandleDataRequest
//                handleDataRequest
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
//            firstArg<()-> TaskDomain>().invoke()
            testTaskDomain(id = newCreatedAt, text = newText, createdAt = newCreatedAt, changedAt = newChangedAt)
        }

        val expected = testTaskDomain(id = newCreatedAt, text = newText, createdAt = newCreatedAt, changedAt = newChangedAt)
        val actual = testRepository.editTask(taskDomainParams)

        assertEquals(expected, actual)
    }

    @Test
    fun `edit task with params result failed`() = runTest {
//        val handleDataRequest = HandleDataRequest(testHandleError)
//        val testRepository =
//            TasksRepositoryImpl(
//                testCacheDataSource,
//                testCloudDataSource,
//                testDataToDomainMapper,
//                testDomainParamsToDataMapper,
//                handleDataRequest
//            )
//        val newCreatedAt = 100L
//        val newChangedAt = 200L
//        val newText = "new text"
//        val taskDomainParams = testTaskDomainParams(text = newText)
//
//        coEvery { testCacheDataSource.getTaskById(any()) } answers {
//            testTaskData(text = "old text", createdAt = newCreatedAt)
//        }
//        coEvery { testDomainParamsToDataMapper.transform(any(), newCreatedAt, newChangedAt) } answers {
//            testTaskData(text = newText, changedAt = newChangedAt)
//        }
//        coEvery { testCacheDataSource.editTask(any()) } answers {
//            firstArg()
//        }
//        coEvery { testCloudDataSource.addTask(any()) } answers {
//            throw NoInternetConnectionException()
//        }
//
//        val expected = "No internet connection"
//        val actual = testRepository.editTask(taskDomainParams)
//
//        assertEquals(expected, actual)
    }
}