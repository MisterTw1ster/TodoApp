package com.example.feature_user_auth.di

import com.example.feature_user_auth.UserAuthFragment
import dagger.Component

@UserAuthScope
@Component(
    modules = [UserAuthModule::class],
    dependencies = [UserAuthDeps::class]
)
internal interface UserAuthComponent {

    fun inject(fragment: UserAuthFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: UserAuthDeps
        ): UserAuthComponent
    }
}