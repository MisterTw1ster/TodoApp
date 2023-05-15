package com.example.user_select.di

import com.example.user_select.view.UserSelectFragment
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