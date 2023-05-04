package com.example.feature_user_select.di

import com.example.feature_user_select.CommunicationUserSelect
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