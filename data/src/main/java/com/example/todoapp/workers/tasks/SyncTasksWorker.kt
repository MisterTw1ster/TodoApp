package com.example.todoapp.workers.tasks

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.todoapp.repository.TasksRepository
import java.util.concurrent.TimeUnit

class SyncTasksWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = try {
//        val repository =
//            (applicationContext as ProvidePeriodicRepository).providePeriodicRepository()
//        repository.syncCacheToCloud()
        Result.success()
    } catch (e: Exception) {
        Result.retry()
    }

    companion object {
        const val WORK_NAME = "SyncTasksWorker"
        private const val REPEAT_INTERVAL = 15L
        fun makeRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequestBuilder<SyncTasksWorker>(REPEAT_INTERVAL, TimeUnit.MINUTES).build()
        }
    }
}

interface ProvidePeriodicRepository {
    fun providePeriodicRepository(): TasksRepository
}