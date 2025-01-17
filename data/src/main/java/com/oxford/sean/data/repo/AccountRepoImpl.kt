package com.oxford.sean.data.repo

import android.accounts.Account
import android.accounts.AccountManager
import com.oxford.sean.data.ApiSource
import com.oxford.sean.dataia.repos.AccountRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepoImpl @Inject constructor(
    private val accountManager: AccountManager,
    private val apiSource: ApiSource
) : AccountRepo {

    companion object {
        private const val ACCOUNT_TYPE = "com.oxford.sean.account"
        private const val AUTH_TOKEN_TYPE = "com.oxford.sean.jwt"
        private const val ACCOUNT_NAME = "user"
    }

    init {
        getAuthToken()?.let { token -> apiSource.token = token }
    }

    override suspend fun login(username: String, password: String) {
        withContext(IO) {
            val response = apiSource.login(username, password)
            val account = Account(ACCOUNT_NAME, ACCOUNT_TYPE)
            if (accountManager.addAccountExplicitly(account, null, null)) {
                accountManager.setAuthToken(account, AUTH_TOKEN_TYPE, response.token)
            }
            apiSource.token = response.token
        }
    }

    override suspend fun logout() {
        val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
        accounts.forEach { accountManager.removeAccountExplicitly(it) }
        apiSource.token = ""
    }

    override suspend fun isUserLoggedIn(): Boolean = getAuthToken() != null

    private fun getAuthToken(): String? {
        val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
        val account = accounts.find { it.name == ACCOUNT_NAME }
        return account?.let { accountManager.peekAuthToken(it, AUTH_TOKEN_TYPE) }
    }
}