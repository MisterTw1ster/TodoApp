package com.example.todoapp.di.users

import com.example.core_domain.exception.HandleError
import com.example.core_domain.exception.users.UsersHandleDomainToUsersResultError
import com.example.core_domain.exception.users.UserResult
import com.example.core_domain.repository.UsersRepository
import com.example.core_users_data_source.cache.UsersCacheDataSourceImpl
import com.example.core_users_data_source.cloud.UsersCloudDataSourceImpl
import com.example.core_users_repository.UsersCacheDataSource
import com.example.core_users_repository.UsersCloudDataSource
import com.example.core_users_repository.UsersRepositoryImpl
import com.example.core.di.qualifier.Users
import com.example.core_users_repository.exception.UsersHandleDataToDomainError
import dagger.Binds
import dagger.Module

@Module
interface UsersBindsModule {

    @Binds
    fun bindUsersCacheDataSourceImpl_to_UsersCacheDataSource(
        usersCacheDataSourceImpl: UsersCacheDataSourceImpl
    ): UsersCacheDataSource

    @Binds
    fun bindUsersCloudDataSourceImpl_to_UsersCloudDataSource(
        usersCloudDataSourceImpl: UsersCloudDataSourceImpl
    ): UsersCloudDataSource

    @Binds
    @Users
    fun bindUsersHandleDataToDomainError_to_HandleErrorException(
        usersHandleDataToDomainError: UsersHandleDataToDomainError
    ): HandleError<Exception>

    @Binds
    fun bindUsersHandleDomainToUsersResultError_to_HandleErrorUsersResult(
        usersHandleDomainToUsersResultError: UsersHandleDomainToUsersResultError
    ): HandleError<UserResult>

    @Binds
    fun bindUsersRepositoryImpl_to_UsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository

}