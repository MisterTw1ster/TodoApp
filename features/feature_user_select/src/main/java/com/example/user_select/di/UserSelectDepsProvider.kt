package com.example.user_select.di

import kotlin.properties.Delegates.notNull

interface UserSelectDepsProvider {
    val deps: UserSelectDeps
    companion object: UserSelectDepsProvider by UserSelectDepsStore
}

object UserSelectDepsStore: UserSelectDepsProvider {
    override var deps: UserSelectDeps by notNull()
}