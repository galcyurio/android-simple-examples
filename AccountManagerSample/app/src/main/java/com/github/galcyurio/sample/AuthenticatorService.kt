package com.github.galcyurio.sample

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @author galcyurio
 */
class AuthenticatorService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        val authenticator = SampleAuthenticator(this)
        return authenticator.iBinder
    }
}