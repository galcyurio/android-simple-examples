package com.github.galcyurio.sample

import android.accounts.AccountManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accountManager = AccountManager.get(this)

        btnAddAccount.setOnClickListener { addNewAccount() }
        btnGetAuthToken.setOnClickListener { }
        btnGetAuthTokenConvenient.setOnClickListener { }
        btnInvalidateAuthToken.setOnClickListener { }
    }

    private fun addNewAccount() {
        val accountType = resources.getString(R.string.account_type)
        val authTokenType = SampleAuthenticator.AUTH_TOKEN_TYPE_FULL_ACCESS
        accountManager.addAccount(
            accountType,
            authTokenType,
            null,
            null,
            this,
            {
                Toast.makeText(this, "Account was created", Toast.LENGTH_SHORT).show()
            },
            null)
    }
}