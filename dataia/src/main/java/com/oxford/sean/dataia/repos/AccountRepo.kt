package com.oxford.sean.dataia.repos

interface AccountRepo {

    suspend fun login(username: String, password: String)

    suspend fun logout()

    suspend fun isUserLoggedIn(): Boolean

}