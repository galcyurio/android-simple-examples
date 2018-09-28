package com.github.galcyurio.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.galcyurio.model.domain.Doggy
import java.util.*

/**
 * @author galcyurio
 */
class DoggyRepository {
    private val doggies = LinkedList<Doggy>()
    private val doggiesLiveData = MutableLiveData<MutableList<Doggy>>()
        .apply { value = doggies }

    fun save(doggy: Doggy) {
        doggies.add(0, doggy)
        doggiesLiveData.postValue(doggies)
    }

    fun getDoggies(): LiveData<MutableList<Doggy>> = doggiesLiveData
}