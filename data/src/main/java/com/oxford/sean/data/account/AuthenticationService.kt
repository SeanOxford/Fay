package com.oxford.sean.data.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder

class AuthenticationService : Service() {
    private var authenticator: Authenticator? = null

    override fun onCreate() {
        authenticator = Authenticator(this)
    }

    override fun onDestroy() {
    }

    override fun onBind(intent: Intent): IBinder? = authenticator?.iBinder
}

class Authenticator(context: Context) : AbstractAccountAuthenticator(context) {

    override fun addAccount(response: AccountAuthenticatorResponse, accountType: String,
                            authTokenType: String, requiredFeatures: Array<String>, options: Bundle
    ): Bundle = Bundle()

    override fun confirmCredentials(response: AccountAuthenticatorResponse, account: Account, options: Bundle): Bundle? {
        return null
    }

    override fun editProperties(response: AccountAuthenticatorResponse, accountType: String): Bundle {
        throw UnsupportedOperationException()
    }

    override fun getAuthToken(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, loginOptions: Bundle): Bundle? {
        return null
    }

    override fun getAuthTokenLabel(authTokenType: String): String? {
        // We don't support multiple token types
        return null
    }

    override fun hasFeatures(response: AccountAuthenticatorResponse, account: Account, features: Array<String>): Bundle {
        val result = Bundle()
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false)
        return result
    }

    override fun updateCredentials(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, loginOptions: Bundle): Bundle? {
        return null
    }
}