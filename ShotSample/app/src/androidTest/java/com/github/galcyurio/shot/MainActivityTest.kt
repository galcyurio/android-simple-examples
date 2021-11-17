package com.github.galcyurio.shot

import androidx.test.core.app.ActivityScenario
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Test

class MainActivityTest : ScreenshotTest {
    @Test
    fun theActivityIsShownProperly() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
            .waitForActivity()
        compareScreenshot(activity)
    }

    @Test
    fun theActivityIsShownProperly2() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
            .waitForActivity()
        compareScreenshot(activity)
    }

    @Test
    fun theActivityIsShownProperly3() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
            .waitForActivity()
        compareScreenshot(activity)
    }
}
