package com.github.galcyurio.sample

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.accounts.AccountManager.KEY_BOOLEAN_RESULT
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @author galcyurio
 */
class SampleAuthenticator(
        private val context: Context
) : AbstractAccountAuthenticator(context) {
    companion object {
        const val AUTH_TOKEN_TYPE = "AUTH_TOKEN_TYPE"
        const val AUTH_TOKEN_TYPE_READ_ONLY = "Read only"
        const val AUTH_TOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an Sample account"
        const val AUTH_TOKEN_TYPE_FULL_ACCESS = "Full access"
        const val AUTH_TOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an Sample account"

        const val ACCOUNT_NAME = "ACCOUNT_NAME"
        const val ACCOUNT_TYPE = "ACCOUNT_TYPE"
    }

    override fun addAccount(response: AccountAuthenticatorResponse?, accountType: String?, authTokenType: String?, requiredFeatures: Array<out String>?, options: Bundle?): Bundle {
        val intent = Intent(context, AuthenticatorActivity::class.java).apply {
            putExtra(ACCOUNT_TYPE, accountType)
            putExtra(AUTH_TOKEN_TYPE, authTokenType)
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        }
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    override fun getAuthToken(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, options: Bundle): Bundle {
        // `Read only`와 `Full access` 이외에는 지원하지 않는다
        if (authTokenType != AUTH_TOKEN_TYPE_READ_ONLY && authTokenType != AUTH_TOKEN_TYPE_FULL_ACCESS) {
            val bundle = Bundle()
            bundle.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType : $authTokenType")
            return bundle
        }

        // `username`과 `password`를 `AccountManager`로부터 알아내서 서버에 적절한 `AuthToken`을 요청한다.
        val am = AccountManager.get(context)
        var authToken = am.peekAuthToken(account, authTokenType)

        // `authToken` 없을 경우 유저에게 인증을 시도할 기회를 준다.
        if (authToken.isNullOrEmpty()) {
            val password = am.getPassword(account)
            authToken = login(password)
        }

        // `authToken`을 받았다면 return 한다
        if (!authToken.isNullOrEmpty()) {
            return Bundle().apply {
                putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
                putString(AccountManager.KEY_AUTHTOKEN, authToken)
            }
        }

        // 여기까지 왔다면 우리는 유저의 비밀번호에 접근할 수 없다는 것이다.
        // 따라서 유저에게서 다시 `credentials`를 받아내야 한다.
        // 이 일은 `AuthenticatorActivity`를 보여주면 된다.
        val intent = Intent(context, AuthenticatorActivity::class.java).apply {
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
            putExtra(AUTH_TOKEN_TYPE, authTokenType)
            putExtra(ACCOUNT_TYPE, account.type)
            putExtra(ACCOUNT_NAME, account.name)
        }
        return Bundle().apply {
            putParcelable(AccountManager.KEY_INTENT, intent)
        }
    }

    fun login(password: String): String = "dummy-auth-token"

    override fun getAuthTokenLabel(authTokenType: String?): String {
        return when (authTokenType) {
            AUTH_TOKEN_TYPE_FULL_ACCESS -> AUTH_TOKEN_TYPE_FULL_ACCESS_LABEL
            AUTH_TOKEN_TYPE_READ_ONLY -> AUTH_TOKEN_TYPE_READ_ONLY_LABEL
            else -> "$authTokenType (Label)"
        }
    }

    override fun hasFeatures(response: AccountAuthenticatorResponse?, account: Account?, features: Array<out String>?): Bundle {
        return Bundle().apply { putBoolean(KEY_BOOLEAN_RESULT, false) }
    }

    override fun editProperties(response: AccountAuthenticatorResponse?, accountType: String?): Bundle? {
        return null
    }

    override fun updateCredentials(response: AccountAuthenticatorResponse?, account: Account?, authTokenType: String?, options: Bundle?): Bundle? {
        return null
    }

    override fun confirmCredentials(response: AccountAuthenticatorResponse?, account: Account?, options: Bundle?): Bundle? {
        return null
    }
}