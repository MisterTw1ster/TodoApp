package com.example.feature_list.di

import com.example.feature_list.CommunicationList
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