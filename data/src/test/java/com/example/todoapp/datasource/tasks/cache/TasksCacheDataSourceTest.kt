package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.core.testTaskCache
import com.example.todoapp.core.testTaskData
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TasksCacheDataSourceTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var testDao: TasksDao

    @MockK
    lateinit var testTaskDataToCacheMapper: TaskDataToCacheMapper

    @MockK
    lateinit var testTaskCacheToDataMapper: TaskCacheToDataMapper

    @Test
    fun `observe tasks return flow TaskData list`() = runTest {
        val testDataSource =
            TasksCacheDataSource(testDao, testTaskDataToCacheMapper, testTaskCacheToDataMapper)

        coEvery { testDao.fetchTasks() } returns flow {
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

        val tasks = testDataSource.fetchTasks().toList()
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
            TasksCacheDataSource(testDao, testTaskDataToCacheMapper, testTaskCacheToDataMapper)

        coEvery { testDao.getTaskById(id) } returns testTaskCache(id = id)

        val expected = testDataSource.getTaskById(id)
        val actual = testTaskData(id = 5L)

        assertEquals(expected, actual)
    }

    @Test
    fun `add new task with param return TaskData`() = runTest {
        val param =
            TaskParamData(text = "new text", importance = "low", deadline = 0L, isDone = true)

        val testDataSource =
            TasksCacheDataSource(testDao, testTaskDataToCacheMapper, testTaskCacheToDataMapper)

        coEvery { testDao.addTask(param) } returns testTaskCache(
            text = param.text, importance = param.importance,
            deadline = param.deadline, isDone = param.isDone
        )

        val expected = testDataSource.addTask(param)
        val actual = TaskData(text = "new text", importance = "low", deadline = 0L, isDone = true)

        assertEquals(expected, actual)
    }

    @Test
    fun `edit task by id with param If changed return true`() = runTest {
        val id = 5L
        val param =
            TaskParamData(text = "edit text", importance = "low", deadline = 0L, isDone = true)

        val testDataSource =
            TasksCacheDataSource(testDao, testTaskDataToCacheMapper, testTaskCacheToDataMapper)

        coEvery { testDao.getTaskById(id) } returns testTaskCache(id = id)
        coEvery { testDao.editTask(id, param) } returns testTaskCache(
            id = id, text = param.text, importance = param.importance,
            deadline = param.deadline, isDone = param.isDone
        )

        val expected = testDataSource.editTask(id, param)
        val actual = true

        assertEquals(expected, actual)
        verify(exactly = 1) { testDao.editTask(id, param) }
    }

    @Test
    fun `edit task by id with param If not changed return false`() = runTest {
        val id = 5L
        val param =
            TaskParamData(text = "edit text", importance = "low", deadline = 0L, isDone = true)

        val testDataSource =
            TasksCacheDataSource(testDao, testTaskDataToCacheMapper, testTaskCacheToDataMapper)

        coEvery { testDao.getTaskById(id) } returns testTaskCache(
            id = id, text = "edit text", importance = "low", isDone = true
        )
        coEvery { testDao.editTask(id, param) } returns testTaskCache(
            id = id, text = param.text, importance = param.importance,
            deadline = param.deadline, isDone = param.isDone
        )

        val expected = testDataSource.editTask(id, param)
        val actual = true

        assertEquals(expected, actual)
        verify(exactly = 0) { testDao.editTask(id, param) }
    }

}