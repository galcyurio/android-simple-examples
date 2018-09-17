package com.github.galcyurio.sample

import android.Manifest
import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerFuture
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TedRx2Permission.with(this)
            .setPermissions(Manifest.permission.GET_ACCOUNTS)
            .request()
            .subscribeBy(
                onSuccess = { if (it.isGranted) getAccounts() },
                onError = { it.printStackTrace() }
            )
    }

    fun getAccounts() {
        val am = AccountManager.get(this)
        val type = "com.github.galcyurio"
        val accounts = am.getAccountsByType(type)
        if (accounts.isEmpty()) {
            // TODO
        }

        val account = Account(accounts[0].name, accounts[0].type)
        val onError = Handler.Callback { true }

        am.getAuthToken(
            account,
            "Manage your tasks",
            null,
            this,
            ::onTokenAcquired,
            Handler(onError)
        )
    }

    fun onTokenAcquired(future: AccountManagerFuture<Bundle>) {
        val result = future.result
        val intent = result.get(AccountManager.KEY_INTENT) as? Intent
        intent?.let { startActivityForResult(intent, 0); return }

        val authToken = result.getString(AccountManager.KEY_AUTHTOKEN)
    }
}