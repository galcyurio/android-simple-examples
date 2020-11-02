package com.github.galcyurio.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(subcomponents = [MainActivitySubcomponent::class])
interface MainActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    fun bindMainAndroidInjectorFactory(factory: MainActivitySubcomponent.Factory): AndroidInjector.Factory<*>
}