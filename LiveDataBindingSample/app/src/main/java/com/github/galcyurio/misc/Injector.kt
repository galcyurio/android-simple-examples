package com.github.galcyurio.misc

import com.github.galcyurio.model.DoggyRepository
import com.github.galcyurio.model.KittyRepository
import com.github.galcyurio.viewmodel.MainViewModelFactory

/**
 * @author galcyurio
 */
object Injector {
    fun provideMainViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory(KittyRepository(), DoggyRepository())
    }
}