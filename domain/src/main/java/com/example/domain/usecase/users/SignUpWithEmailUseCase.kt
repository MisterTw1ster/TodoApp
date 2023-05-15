package com.example.domain.usecase.users

import com.example.domain.exception.users.UsersHandleRequest
import com.example.domain.models.UserDomainParams
import com.example.domain.repository.UsersRepository
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
