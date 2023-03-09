package com.example.todoapp.workers.tasks

import android.content.Context
import androidx.work.*
import com.example.todoapp.repository.TasksRepository
import java.util.concurrent.TimeUnit

class SyncTasksWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = try {
        val repository =
            (applicationContext as ProvidePeriodicRepository).providePeriodicRepository()
        repository.syncCacheToCloud()
        Result.success()
    } catch (e: Exception) {
        Result.retry()
    }

    companion object {
        const val WORK_NAME = "SyncTasksWorker"
        private const val REPEAT_INTERVAL = 15L
        private val TIME_UNIT = TimeUnit.MINUTES
        fun makeRequest(): PeriodicWorkRequest {
            return PeriodicWorkRequestBuilder<SyncTasksWorker>(
                REPEAT_INTERVAL,
                TIME_UNIT
            ).setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            ).setInitialDelay(
                REPEAT_INTERVAL,
                TIME_UNIT
            ).build()
        }
    }
}

interface ProvidePeriodicRepository {
    fun providePeriodicRepository(): TasksRepository
}