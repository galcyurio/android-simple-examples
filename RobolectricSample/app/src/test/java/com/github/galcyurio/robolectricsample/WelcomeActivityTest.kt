package com.github.galcyurio.robolectricsample

import android.content.Intent
import kotlinx.android.synthetic.main.activity_welcome.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.setupActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WelcomeActivityTest {

    @Test
    fun `로그인 버튼을 누르면 LoginActivity 시작`() {
        val welcomeActivity = setupActivity(WelcomeActivity::class.java)
        welcomeActivity.login.performClick()

        val expectedIntent = Intent(welcomeActivity, LoginActivity::class.java)
        val actualIntent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }
}
