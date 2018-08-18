package com.github.galcyurio.livedataupdownsample

import android.arch.lifecycle.MutableLiveData

class NumberViewModel {
    val number = MutableLiveData<Int>().apply { value = 0 }

    fun plus() {
        number.value = number.value?.plus(1)
    }

    fun minus() {
        number.value = number.value?.minus(1)
    }
}