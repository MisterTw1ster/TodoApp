package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.core.testTaskCache
import com.example.todoapp.core.testTaskData
import com.example.todoapp.models.TaskData
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataToCacheMapper
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
    lateinit var testDataToCacheMapper: TaskDataToCacheMapper
    @MockK
    lateinit var testCacheToDataMapper: TaskCacheToDataMapper

    @Test
    fun `observe tasks return flow TaskData list`() = runTest {
        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testCacheToDataMapper,
                testDataToCacheMapper
            )

        coEvery { testDao.observeTasks() } returns flow {
            emit(listOf(testTaskCache(id = 1),testTaskCache(id = 2)))
            emit(listOf(testTaskCache(id = 1),testTaskCache(id = 3)))
        }

        coEvery { testCacheToDataMapper.transform(any()) } answers {
            testTaskData(id = firstArg<TaskCache>().id)
        }

        val tasks = testDataSource.observeTasks().toList()
        val expectedFirst = listOf(testTaskData(id = 1), testTaskData(id = 2))
        val actualFirst = tasks[0]
        val expectedSecond = listOf(testTaskData(id = 1), testTaskData(id = 3))
        val actualSecond = tasks[1]

        assertEquals(expectedFirst, actualFirst)
        assertEquals(expectedSecond, actualSecond)
    }

    @Test
    fun `get task by id return TaskData`() = runTest {
        val id = 5L

        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testCacheToDataMapper,
                testDataToCacheMapper
            )

        coEvery { testDao.getTaskById(id) } returns testTaskCache(id = id)
        coEvery { testCacheToDataMapper.transform(any()) } returns testTaskData(id)

        val expected = testTaskData(id = 5L)
        val actual = testDataSource.getTaskById(id)

        assertEquals(expected, actual)
    }

    @Test
    fun `add new task return TaskData`() = runTest {
        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testCacheToDataMapper,
                testDataToCacheMapper
            )
        val taskData = testTaskData(id = 0L)
        val newId = 5L

        coEvery { testDataToCacheMapper.transform(any()) } answers {
            testTaskCache(id = firstArg<TaskData>().id)
        }
        coEvery { testDao.addTask(any()) } returns newId

        val expected = testTaskData(id = newId)
        val actual = testDataSource.addTask(taskData)

        assertEquals(expected, actual)
    }

    @Test
    fun `edit task if changed return new TaskData`() = runTest {
        val testDataSource =
            TasksCacheDataSourceImpl(
                testDao,
                testCacheToDataMapper,
                testDataToCacheMapper
            )
//        val newText = "new text"
//        val createdAt = 100L
//        val taskData = testTaskData(text = newText)
        val taskData = testTaskData()
//        coEvery { testDao.getTaskById(any()) } answers {
//            testTaskCache(text = "old text", createdAt = createdAt)
//        }
        coEvery { testDataToCacheMapper.transform(any()) } answers {
//            testTaskCache(text = firstArg<TaskData>().text, createdAt = firstArg<TaskData>().createdAt)
            testTaskCache()
        }
        coEvery { testDao.editTask(any()) } just Runs
//        coEvery { testCacheToDataMapper.transform(any()) } answers {
//            testTaskData(text = firstArg<TaskCache>().text, createdAt = firstArg<TaskData>().createdAt)
//        }

//        val expected = testTaskData(text = newText, createdAt = createdAt)
        val expected = testTaskData()
        val actual = testDataSource.editTask(taskData)

        assertEquals(expected, actual)
//        coVerify(exactly = 1) { testDao.editTask(any()) }
    }

//    @Test
//    fun `edit task if not changed return current TaskData`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//        val taskData = testTaskData(changedAt = 100L)
//
//        coEvery { testDao.getTaskById(any()) } returns testTaskCache()
//        coEvery { testCacheToDataMapper.transform(any()) } answers {
//            testTaskData()
//        }
//
//        val expected = testTaskData()
//        val actual = testDataSource.editTask(taskData)
//
//        assertEquals(expected, actual)
//        coVerify(exactly = 0) { testDao.editTask(any()) }
//    }

}