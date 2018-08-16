package com.github.galcyurio.roomtodo

import android.app.Application
import com.github.galcyurio.roomtodo.misc.module
import org.koin.android.ext.android.startKoin

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(module))
    }
}