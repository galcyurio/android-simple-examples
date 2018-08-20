package com.github.galcyurio.lifecycletoastsample

import android.arch.lifecycle.LifecycleObserver

interface MainContract {
    interface View {
        fun showCurrentLifecycle()
    }

    interface Presenter : LifecycleObserver
}