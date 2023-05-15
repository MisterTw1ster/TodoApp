package com.example.user_auth.di

import com.example.user_auth.view.CommunicationUserAuth
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