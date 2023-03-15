package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.HandleError
import com.example.todoapp.models.AuthResult
import com.example.todoapp.models.UserDomainParams
import com.example.todoapp.repository.AuthRepository
import com.example.todoapp.repository.TasksRepository
import javax.inject.Inject

@DomainScope
class SignUpWithEmailUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val authRepository: AuthRepository,
    private val handleError: HandleError<String>
) {
    suspend operator fun invoke(userParams: UserDomainParams): AuthResult = try {
        val userDomain = authRepository.signUpWithEmail(userParams)
//        tasksRepository.addUserBranch(userDomain.localId)
        AuthResult.Success(userDomain)
    } catch (e: Exception) {
        AuthResult.Failure(handleError.handle(e))
    }
}
