package com.github.galcyurio.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.galcyurio.model.KittyRepository

/**
 * @author galcyurio
 */
class MainViewModelFactory(
    private val kittyRepository: KittyRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(kittyRepository) as T
}