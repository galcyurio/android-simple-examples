package com.github.galcyurio.textholdermvvm

import android.app.Application
import com.github.galcyurio.textholdermvvm.misc.koinModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(koinModule))
    }
}