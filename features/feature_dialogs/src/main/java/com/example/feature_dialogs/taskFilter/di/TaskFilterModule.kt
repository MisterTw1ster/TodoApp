package com.example.feature_dialogs.taskFilter.di

import com.example.feature_dialogs.taskFilter.view.CommunicationTaskFilter
import dagger.Module
import dagger.Provides

@Module
class TaskFilterModule {

    @TaskFilterScope
    @Provides
    fun provideCommunicationTaskFilter(): CommunicationTaskFilter {
        return CommunicationTaskFilter()
    }

}