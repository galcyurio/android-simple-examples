package com.github.galcyurio.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.galcyurio.model.domain.Kitty
import java.util.*

class KittyRepository {
    private val kitties = LinkedList<Kitty>()
    private val kittiesLiveData = MutableLiveData<MutableList<Kitty>>()
        .apply { value = kitties }

    fun save(kitty: Kitty) {
        kitties.add(0, kitty)
        kittiesLiveData.postValue(kitties)
    }

    fun getKitties(): LiveData<MutableList<Kitty>> = kittiesLiveData
}
