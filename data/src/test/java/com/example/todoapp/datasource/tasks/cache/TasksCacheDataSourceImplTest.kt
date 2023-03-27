//package com.example.todoapp.datasource.tasks.cache
//
//import com.example.todoapp.core.testTaskCache
//import com.example.todoapp.core.testTaskData
//import com.example.todoapp.models.TaskData
//import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
//import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataToCacheMapper
//import io.mockk.*
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit4.MockKRule
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.toList
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Rule
//import org.junit.Test
//
//class TasksCacheDataSourceImplTest {
//
//    @get:Rule
//    val rule = MockKRule(this)
//
//    @MockK
//    lateinit var testDao: TasksDao
//    @MockK
//    lateinit var testDataToCacheMapper: TaskDataToCacheMapper
//    @MockK
//    lateinit var testCacheToDataMapper: TaskCacheToDataMapper
//
//    @Test
//    fun `observe tasks return flow ListTaskData`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//
//        coEvery { testCacheToDataMapper.transform(any()) } answers {
//            testTaskData(id = firstArg<TaskCache>().id)
//        }
//
//        coEvery { testDao.observeTasks() } returns flow {
//            emit(listOf(testTaskCache(id = 1),testTaskCache(id = 2)))
//            emit(listOf(testTaskCache(id = 1),testTaskCache(id = 3)))
//        }
//
//
//        val tasks = testDataSource.observeTasks().toList()
//        val expectedFirst = listOf(testTaskData(id = 1), testTaskData(id = 2))
//        val actualFirst = tasks[0]
//        val expectedSecond = listOf(testTaskData(id = 1), testTaskData(id = 3))
//        val actualSecond = tasks[1]
//
//        assertEquals(expectedFirst, actualFirst)
//        assertEquals(expectedSecond, actualSecond)
//    }
//
//    @Test
//    fun `get task by id return TaskData`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//        val id = 5L
//
//        coEvery { testDao.getTaskById(any()) } answers {
//            testTaskCache(id = firstArg())
//        }
//        coEvery { testCacheToDataMapper.transform(any()) } answers {
//            testTaskData(id = firstArg<TaskCache>().id)
//        }
//
//        val expected = testTaskData(id = id)
//        val actual = testDataSource.getTaskById(id)
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `add new task return TaskData`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//        val taskData = testTaskData()
//
//        coEvery { testDataToCacheMapper.transform(any()) } answers {
//            testTaskCache(id = firstArg<TaskData>().id)
//        }
//        coEvery { testDao.addTask(any()) } answers {
//            firstArg<TaskCache>().id
//        }
//
//        val expected = testTaskData()
//        val actual = testDataSource.addTask(taskData)
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `edit task return TaskData`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//        val taskData = testTaskData()
//
//        coEvery { testDataToCacheMapper.transform(any()) } answers {
//            testTaskCache(id = firstArg<TaskData>().id)
//        }
//
//        coEvery { testDao.editTask(any()) } just Runs
//        val expected = testTaskData()
//        val actual = testDataSource.editTask(taskData)
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `delete task by id`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//        val id = 2L
//        val tasksCache = mutableListOf(testTaskCache(id = id))
//
//        coEvery { testDao.deleteTaskById(any()) } answers {
//            val element = tasksCache.find { id == firstArg() }
//            tasksCache.remove(element)
//
//        }
//
//        testDataSource.deleteTaskById(id)
//        val expected = emptyList<TaskCache>()
//        val actual = tasksCache
//
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun `replace tasks`() = runTest {
//        val testDataSource =
//            TasksCacheDataSourceImpl(
//                testDao,
//                testCacheToDataMapper,
//                testDataToCacheMapper
//            )
//        val tasksCache = mutableListOf(
//            testTaskCache(id = 1L),
//            testTaskCache(id = 2L)
//        )
//        val newTasksData = mutableListOf(
//            testTaskData(id = 3L),
//            testTaskData(id = 4L)
//        )
//
//        coEvery { testDataToCacheMapper.transform(any()) } answers {
//            testTaskCache(id = firstArg<TaskData>().id)
//        }
//        coEvery { testDao.replaceAll(any()) } answers {
//            tasksCache.clear()
//            tasksCache.addAll(firstArg())
//        }
//
//        testDataSource.replaceTasks(newTasksData)
//        val expected = mutableListOf(
//            testTaskCache(id = 3L),
//            testTaskCache(id = 4L)
//        )
//        val actual = tasksCache
//
//        assertEquals(expected, actual)
//    }
//
//}