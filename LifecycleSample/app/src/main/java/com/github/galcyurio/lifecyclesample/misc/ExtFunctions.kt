package com.github.galcyurio.lifecyclesample.misc

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver

fun <T : LifecycleObserver> T.addTo(lifecycle: Lifecycle): T = apply {
    lifecycle.addObserver(this)
}