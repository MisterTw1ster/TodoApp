package com.example.feature_tasks_filters.di

import com.example.feature_tasks_filters.CommunicationTasksFilters
import dagger.Module
import dagger.Provides

@Module
class TasksFiltersModule {

    @TasksFiltersScope
    @Provides
    fun provideCommunicationTasksFilters(): CommunicationTasksFilters {
        return CommunicationTasksFilters()
    }

}