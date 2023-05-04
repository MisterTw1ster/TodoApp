package com.example.feature_tasks_sorting.di

import com.example.feature_tasks_sorting.CommunicationTasksSorting
import dagger.Module
import dagger.Provides

@Module
class TasksSortingModule {

    @TasksSortingScope
    @Provides
    fun provideCommunicationTasksSorting(): CommunicationTasksSorting {
        return CommunicationTasksSorting()
    }

}