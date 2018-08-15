package com.github.galcyurio.textholdermvvm.misc

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
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

fun <T : ViewDataBinding> AppCompatActivity.setContentView2(
    @LayoutRes layoutId: Int): T {
    return DataBindingUtil.setContentView(this, layoutId)
}