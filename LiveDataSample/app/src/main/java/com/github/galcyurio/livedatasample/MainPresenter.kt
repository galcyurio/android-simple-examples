package com.github.galcyurio.livedatasample

import android.arch.lifecycle.MutableLiveData

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    val intLiveData = MutableLiveData<Int>().apply { value = 0 }

    fun plus() {
        intLiveData.value = intLiveData.value?.plus(1)
    }

    fun minus() {
        intLiveData.value = intLiveData.value?.minus(1)
    }
}
