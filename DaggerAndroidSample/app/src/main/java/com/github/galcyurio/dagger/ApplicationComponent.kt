package com.github.galcyurio.dagger

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        MainActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)
internal interface ApplicationComponent {
    fun inject(application: SampleApplication)
}