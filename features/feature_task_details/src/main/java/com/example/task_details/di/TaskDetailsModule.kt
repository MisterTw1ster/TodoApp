package com.example.task_details.di

import com.example.core.DateLongToString
import com.example.task_details.view.CommunicationTaskDetails
import dagger.Module
import dagger.Provides

@Module
class TaskDetailsModule {

    @TaskDetailsScope
    @Provides
    fun provideCommunicationTaskDetails(dateLongToString: DateLongToString): CommunicationTaskDetails {
        return CommunicationTaskDetails(dateLongToString = dateLongToString)
    }

}