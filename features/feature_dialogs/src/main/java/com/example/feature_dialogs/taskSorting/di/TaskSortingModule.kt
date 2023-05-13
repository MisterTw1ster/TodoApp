package com.example.feature_dialogs.taskSorting.di

import com.example.feature_dialogs.taskSorting.view.CommunicationTaskSorting
import dagger.Module
import dagger.Provides

@Module
class TaskSortingModule {

    @TaskSortingScope
    @Provides
    fun provideCommunicationTasksSorting(): CommunicationTaskSorting {
        return CommunicationTaskSorting()
    }

}