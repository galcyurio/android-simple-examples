package com.github.galcyurio.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainFragmentModule::class])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}