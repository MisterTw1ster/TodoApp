package com.example.todoapp.di

import com.example.todoapp.exception.HandleError
import dagger.Binds
import dagger.Module

@Module
interface DomainBindModule {

    @Binds
    fun bindHandleDomainError_to_HandleError(
        handleDomainError: HandleError.HandleDomainError
    ): HandleError<String>

}