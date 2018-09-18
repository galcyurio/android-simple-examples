package com.github.galcyurio.sample

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authenticator.*

class AuthenticatorActivity : AppCompatActivity() {
    private lateinit var accountManager: AccountManager
    private lateinit var accountType: String
    private lateinit var authTokenType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticator)

        accountManager = AccountManager.get(this)

        val accountName = intent.getStringExtra(SampleAuthenticator.ACCOUNT_NAME)
        accountType = intent.getStringExtra(SampleAuthenticator.ACCOUNT_TYPE)
        authTokenType = intent.getStringExtra(SampleAuthenticator.AUTH_TOKEN_TYPE)

        email.setText(accountName)
        signIn.setOnClickListener { submit() }
        signUp.setOnClickListener { /* TODO: show SignUpActivity */ }
    }

    fun submit() {
        val email = email.text.toString()
        val password = password.text.toString()
        val authToken = AuthUseCase.signIn(email, password) // 서버에 로그인 요청 (실제로는 비동기)

        if (authToken.isNullOrEmpty()) {
            
        } else {
            val account = Account(email, accountType)
            accountManager.addAccountExplicitly(account, password, null)
            accountManager.setAuthToken(account, authTokenType, authToken)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
