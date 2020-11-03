package com.github.galcyurio.roomtodo

import android.app.Application
import com.github.galcyurio.roomtodo.misc.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TodoApp)
            modules(appModule)
        }
    }
}