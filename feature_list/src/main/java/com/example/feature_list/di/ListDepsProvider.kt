package com.example.feature_list.di

import kotlin.properties.Delegates.notNull

interface ListDepsProvider {
    val deps: ListDeps
    companion object: ListDepsProvider by ListDepsStore
}

object ListDepsStore: ListDepsProvider {
    override var deps: ListDeps by notNull()
}