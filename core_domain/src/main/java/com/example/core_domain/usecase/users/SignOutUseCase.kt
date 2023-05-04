package com.example.core_domain.usecase.users

import com.example.core_domain.repository.UsersRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignOutUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.signOut()
    }
}