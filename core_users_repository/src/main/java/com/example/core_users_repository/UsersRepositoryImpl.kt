package com.example.core_users_repository

import com.example.core.di.scope.AppScope
import com.example.domain.models.UserDomain
import com.example.domain.models.UserDomainParams
import com.example.domain.repository.UsersRepository
import com.example.core_users_repository.exception.UsersHandleDataRequest
import com.example.core_users_repository.mappers.UserDataToDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class UsersRepositoryImpl @Inject constructor(
    private val usersCacheDataSource: UsersCacheDataSource,
    private val usersCloudDataSource: UsersCloudDataSource,
    private val dataToDomainMapper: UserDataToDomainMapper,
    private val usersHandleDataRequest: UsersHandleDataRequest
//    private val handleError: AuthHandleDomainError
) : UsersRepository {

    override suspend fun fetchLocalUsers(): List<UserDomain> {
        val usersDomain = usersCacheDataSource.fetchUsers()
        return usersDomain.map { user ->
            dataToDomainMapper.transform(user)
        }
    }

    override suspend fun observeCurrentUser(): Flow<UserDomain?> {
        return usersCacheDataSource.observeCurrentUser().map { user ->
            user?.let { dataToDomainMapper.transform(user) }
        }
    }

    override suspend fun getCurrentUser(): UserDomain? {
        val userData = usersCacheDataSource.getCurrentUser()
        return userData?.let { dataToDomainMapper.transform(it) }
    }

    override suspend fun setCurrentUserId(userId: String): Boolean {
        usersCacheDataSource.setCurrentUserId(userId)
        return true
    }

    override suspend fun signUpWithEmail(userParams: UserDomainParams): UserDomain {
        return usersHandleDataRequest.handle {
            val userData = usersCloudDataSource.signUpWithEmail(userParams.email, userParams.password)
            usersCacheDataSource.addUser(userData)
            usersCacheDataSource.setCurrentUserId(userData.localId)
            dataToDomainMapper.transform(userData)
        }
    }
//    {
//        try {
//            val userData = usersCloudDataSource.signUpWithEmail(userParams.email, userParams.password)
//            usersCacheDataSource.addUser(userData)
//            usersCacheDataSource.setCurrentUserId(userData.localId)
//            return dataToDomainMapper.transform(userData)
//        } catch (e: Exception) {
//            throw handleError.handle(e)
//        }
//    }

    override suspend fun signInWithEmail(userParams: UserDomainParams): UserDomain {
        return usersHandleDataRequest.handle {
            val userData = usersCloudDataSource.signInWithEmail(userParams.email, userParams.password)
            usersCacheDataSource.addUser(userData)
            usersCacheDataSource.setCurrentUserId(userData.localId)
            dataToDomainMapper.transform(userData)
        }
    }
//    {
//        try {
//            val userData = usersCloudDataSource.signInWithEmail(userParams.email, userParams.password)
//            usersCacheDataSource.addUser(userData)
//            usersCacheDataSource.setCurrentUserId(userData.localId)
//            return dataToDomainMapper.transform(userData)
//      } catch (e: Exception) {
//            throw handleError.handle(e)
//        }
//    }

    override suspend fun signOut(): Boolean {
        usersCacheDataSource.clearCurrentUserId()
        return true
    }

}