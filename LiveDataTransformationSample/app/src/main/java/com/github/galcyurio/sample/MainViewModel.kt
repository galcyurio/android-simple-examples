package com.github.galcyurio.sample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.galcyurio.sample.data.UserRepository

/**
 * @author galcyurio
 */
class MainViewModel : ViewModel() {
    val name: LiveData<String>
    val nickname: LiveData<String>

    init {
        val user = UserRepository.getUser()
        name = Transformations.map(user) { it.name }
        nickname = Transformations.map(user) { it.nickname }
    }
}