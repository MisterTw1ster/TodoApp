//package com.example.core_task_data.usecase
//
//import com.example.core_domain.repository.SettingsRepository
//import com.example.core_domain.repository.TasksRepository
//import com.example.core_domain.usecase.tasks.ObserveTasksUseCase
//import com.example.core_task_data.core.testTaskDomain
//import io.mockk.coEvery
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit4.MockKRule
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.toList
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert
//import org.junit.Rule
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class ObserveTasksUseCaseTest {
//
//    @get:Rule
//    val rule = MockKRule(this)
//
//    @MockK
//    lateinit var tasksRepository: TasksRepository
//    @MockK
//    lateinit var settingsRepository: SettingsRepository
//
//    @Test
//    fun `observe tasks return flow sort list by created_at_desc`() = runTest {
//        val useCase =
//            ObserveTasksUseCase(
//                tasksRepository,
//                settingsRepository
//            )
//        val userId = "id1"
//        val hideCompleted = false
//        val sortMode = "created_at_desc"
//
//        coEvery { tasksRepository.initAndObserveTasks(any()) } returns flow {
//            emit(listOf(testTaskDomain(id = 1, userId = userId), testTaskDomain(id = 2, userId = userId)))
//            emit(listOf(testTaskDomain(id = 1, userId = userId), testTaskDomain(id = 3, userId = userId)))
//        }
//
//        coEvery { settingsRepository.observeHideCompletedFilters() } returns flow {
//            emit(hideCompleted)
//        }
//
//        coEvery { settingsRepository.observeSortingTasks() } returns flow {
//            emit(sortMode)
//        }
//
//        val tasks = useCase.invoke(userId).toList()
//        val expectedFirst = listOf(
//            testTaskDomain(id = 1, userId = userId),
//            testTaskDomain(id = 2, userId = userId)
//        )
//        val actualFirst = tasks[0]
//        val expectedSecond = listOf(
//            testTaskDomain(id = 1, userId = userId),
//            testTaskDomain(id = 3, userId = userId)
//        )
//        val actualSecond = tasks[1]
//
//        Assert.assertEquals(expectedFirst, actualFirst)
//        Assert.assertEquals(expectedSecond, actualSecond)
//    }
//
//}