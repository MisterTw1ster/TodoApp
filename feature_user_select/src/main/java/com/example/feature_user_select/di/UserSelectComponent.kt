package com.example.feature_user_select.di

import com.example.feature_user_select.UserSelectFragment
import dagger.Component

@UserSelectScope
@Component(
    modules = [UserSelectModule::class],
    dependencies = [UserSelectDeps::class]
)
internal interface UserSelectComponent {

    fun inject(fragment: UserSelectFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: UserSelectDeps
        ): UserSelectComponent
    }
}