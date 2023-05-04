package com.example.core_domain.usecase.users

import com.example.core_domain.exception.users.UsersHandleRequest
import com.example.core_domain.models.UserDomainParams
import com.example.core_domain.repository.UsersRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignUpWithEmailUseCase @Inject constructor(
    private val repository: UsersRepository,
    private val handleRequest: UsersHandleRequest
) {
    suspend operator fun invoke(userParams: UserDomainParams) = handleRequest.handle {
        repository.signUpWithEmail(userParams)
    }

}
