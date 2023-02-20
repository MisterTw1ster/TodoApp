package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.core.testTaskCache
import com.example.todoapp.core.testTaskData
import com.example.todoapp.datasource.TaskDataParams
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataParamsToCacheMapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TasksCacheDataSourceImplTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var testDao: TasksDao

    @MockK
    lateinit var testTaskDataParamsToCacheMapper: TaskDataParamsToCacheMapper

    @MockK
    lateinit var testTaskCacheToDataMapper: TaskCacheToDataMapper

    @Test
    fun `observe tasks return flow TaskData list`() = runTest {
        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testTaskCacheToDataMapper,
                testTaskDataParamsToCacheMapper
            )

        coEvery { testDao.observeTasks() } returns flow {
            emit(
                listOf(
                    testTaskCache(id = 1),
                    testTaskCache(id = 2)
                )
            )
            emit(
                listOf(
                    testTaskCache(id = 1),
                    testTaskCache(id = 3)
                )
            )
        }

        coEvery { testTaskCacheToDataMapper.transform(any()) } answers {
            testTaskData(id = firstArg<TaskCache>().id)
        }

        val tasks = testDataSource.observeTasks().toList()
        val expectedFirst = tasks[0]
        val actualFirst = listOf(testTaskData(id = 1), testTaskData(id = 2))
        val expectedSecond = tasks[1]
        val actualSecond = listOf(testTaskData(id = 1), testTaskData(id = 3))

        assertEquals(expectedFirst, actualFirst)
        assertEquals(expectedSecond, actualSecond)
    }

    @Test
    fun `get task by id return TaskData`() = runTest {
        val id = 5L

        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testTaskCacheToDataMapper,
                testTaskDataParamsToCacheMapper
            )

        coEvery { testDao.getTaskById(id) } returns testTaskCache(id = id)
        coEvery { testTaskCacheToDataMapper.transform(any()) } returns testTaskData(id)

        val expected = testDataSource.getTaskById(id)
        val actual = testTaskData(id = 5L)

        assertEquals(expected, actual)
    }

    @Test
    fun `add new task with param return id`() = runTest {
        val params =
            TaskDataParams(text = "new text", importance = "low", deadline = 0L, isDone = true)

        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testTaskCacheToDataMapper,
                testTaskDataParamsToCacheMapper
            )

        coEvery {
            testTaskDataParamsToCacheMapper.transform(0L, any(), any(), any())
        } answers {
            testTaskCache(id = firstArg())
        }
        coEvery { testDao.addTask(any()) } answers {
            firstArg<TaskCache>().id
        }

        val expected = testDataSource.addTask(params)
        val actual = 0L

        assertEquals(expected, actual)
    }

    @Test
    fun `edit task by id with param If changed return true`() = runTest {
        val id = 5L
        val params =
            TaskDataParams(text = "edit text", importance = "low", deadline = 0L, isDone = true)

        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testTaskCacheToDataMapper,
                testTaskDataParamsToCacheMapper
            )

        coEvery { testDao.getTaskById(id) } returns testTaskCache(id = id)
        coEvery { testTaskDataParamsToCacheMapper.transform(any(), any(), any(), any()) } answers {
            testTaskCache(id = firstArg())
        }
        coEvery { testDao.editTask(any()) } just Runs

        val expected = testDataSource.editTask(id, params)
        val actual = true

        assertEquals(expected, actual)
        coVerify(exactly = 1) { testDao.editTask(any()) }
    }

    @Test
    fun `edit task by id with param If not changed return false`() = runTest {
        val id = 5L
        val params =
            TaskDataParams(text = "edit text", importance = "low", deadline = 0L, isDone = true)

        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testTaskCacheToDataMapper,
                testTaskDataParamsToCacheMapper
            )

        coEvery { testDao.getTaskById(id) } returns testTaskCache(
            id = id, text = "edit text", importance = "low", isDone = true
        )

        val expected = testDataSource.editTask(id, params)
        val actual = false

        assertEquals(expected, actual)
        coVerify(exactly = 0) { testDao.editTask(any()) }
    }

}