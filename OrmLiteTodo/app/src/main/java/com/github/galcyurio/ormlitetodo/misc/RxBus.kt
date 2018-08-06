package com.github.galcyurio.ormlitetodo.misc

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {
    private val subject = PublishSubject.create<Any>()

    fun publish(event: Any) {
        subject.onNext(event)
    }

    fun <T> register(eventType: Class<T>): Observable<T> {
        return subject.ofType(eventType)
    }
}