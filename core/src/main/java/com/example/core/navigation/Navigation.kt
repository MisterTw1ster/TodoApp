package com.example.core.navigation

interface Navigation {
    fun showListReplace(userId: String)
    fun showTaskDetailsAdd(userId: String, itemId: Long)
    fun showUserSelectReplace()
    fun showAuthUserReplace()
    fun showAuthUserAdd()
    fun showImportanceTaskDetails()
    fun pop()
    fun showFilter()
    fun showSorting()

}