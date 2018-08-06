package com.github.galcyurio.lifecyclesample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.orhanobut.logger.Logger

class MainPresenter : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        Logger.i("Lifecycle.Event.ON_CREATE !!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        Logger.i("Lifecycle.Event.ON_START !!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        Logger.i("Lifecycle.Event.ON_STOP !!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        Logger.i("Lifecycle.Event.ON_DESTROY !!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private fun onAny() {
        Logger.i("Lifecycle.Event.ON_ANY !!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Logger.i("Lifecycle.Event.ON_RESUME !!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        Logger.i("Lifecycle.Event.ON_PAUSE !!")
    }

}