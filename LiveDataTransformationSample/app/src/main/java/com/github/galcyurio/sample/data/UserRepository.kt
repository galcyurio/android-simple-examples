package com.github.galcyurio.sample.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

/**
 * @author galcyurio
 */
object UserRepository {

    fun getUser(): LiveData<User> {
        val user = User("test name", "test nickname")
        return MutableLiveData<User>()
                .apply { value = user }
    }
}