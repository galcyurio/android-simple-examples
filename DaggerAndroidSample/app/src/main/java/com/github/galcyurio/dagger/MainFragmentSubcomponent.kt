package com.github.galcyurio.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MainFragmentSubcomponent : AndroidInjector<MainFragment> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainFragment>
}