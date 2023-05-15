package com.example.task_list.di

import com.example.task_list.view.CommunicationList
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @ListScope
    @Provides
    fun provideCommunicationList(): CommunicationList {
        return CommunicationList()
    }

}