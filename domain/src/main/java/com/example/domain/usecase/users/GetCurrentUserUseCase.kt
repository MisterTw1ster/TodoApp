package com.example.domain.usecase.users

import com.example.domain.models.UserDomain
import com.example.domain.repository.UsersRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(): UserDomain? {
        return repository.getCurrentUser()
    }
}
