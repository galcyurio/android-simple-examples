package com.github.galcyurio.textholdermvvm.misc

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.galcyurio.textholdermvvm.viewmodel.TextViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val koinModule = applicationContext {
    bean<SharedPreferences> {
        PreferenceManager.getDefaultSharedPreferences(androidApplication())
    }
    bean { LocalRepository(get()) }

    viewModel { TextViewModel(get()) }
}