package com.github.galcyurio.lifecycletoastsample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {
        view.showCurrentLifecycle()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun subscribe() {
        /* 구독 시작, 커넥션 연결 */
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe() {
        /* 구독 해제, 커넥션 끊기 */
    }
}