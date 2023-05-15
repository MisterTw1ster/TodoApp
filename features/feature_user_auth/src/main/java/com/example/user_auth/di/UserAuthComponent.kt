package com.example.user_auth.di

import com.example.user_auth.view.UserAuthFragment
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