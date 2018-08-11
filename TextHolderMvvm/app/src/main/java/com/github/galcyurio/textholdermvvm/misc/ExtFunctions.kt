package com.github.galcyurio.textholdermvvm.misc

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.widget.EditText

fun <T : ViewModel> AppCompatActivity.getViewModel(
    clazz: Class<T>,
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProviders.of(this, factory).get(clazz)
}

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner,
                            observer: (T) -> Unit) {
    observe(lifecycleOwner, Observer { it?.run(observer) })
}

fun EditText.string(): String = text.toString()