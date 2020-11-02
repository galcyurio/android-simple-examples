package com.github.galcyurio.dagger

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainFragmentSubcomponent::class])
interface MainFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(MainFragment::class)
    fun bindMainFragmentInjectorFactory(factory: MainFragmentSubcomponent.Factory): AndroidInjector.Factory<*>
}