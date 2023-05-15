package com.example.user_select.di

import com.example.user_select.view.CommunicationUserSelect
import dagger.Module
import dagger.Provides

@Module
class UserSelectModule {

    @UserSelectScope
    @Provides
    fun provideSelectUserCommunication(): CommunicationUserSelect {
        return CommunicationUserSelect()
    }

}