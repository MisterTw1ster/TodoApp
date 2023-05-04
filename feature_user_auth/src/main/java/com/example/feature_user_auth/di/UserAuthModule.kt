package com.example.feature_user_auth.di

import com.example.feature_user_auth.CommunicationUserAuth
import dagger.Module
import dagger.Provides

@Module
class UserAuthModule {

    @UserAuthScope
    @Provides
    fun provideUserAuthCommunication(): CommunicationUserAuth {
        return CommunicationUserAuth()
    }

}