package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.AuthRepository
import com.example.todoapp.repository.TasksRepository
import javax.inject.Inject

@DomainScope
class SignUpWithEmailUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): String {
        val userId = authRepository.signUpWithEmail(email = email, password = password)
        return tasksRepository.addUserBranch(userId)
    }
}