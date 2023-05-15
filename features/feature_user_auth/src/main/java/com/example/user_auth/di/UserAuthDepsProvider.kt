package com.example.user_auth.di

import kotlin.properties.Delegates.notNull

interface UserAuthDepsProvider {
    val deps: UserAuthDeps
    companion object: UserAuthDepsProvider by UserAuthDepsStore
}

object UserAuthDepsStore: UserAuthDepsProvider {
    override var deps: UserAuthDeps by notNull()
}