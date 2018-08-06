package com.github.galcyurio.livedatasample

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* LiveData.observe() 는 lifecycle이 Destroyed가 되면 자동으로 해제된다. */
        /* LiveData.observeForever() 는 LiveData.removeObserver() 를 호출하기 전까지 해제되지 않는다. */
        presenter.intLiveData.observe(this) { txtNumber.text = it.toString() }

        btnDown.setOnClickListener { presenter.minus() }
        btnUp.setOnClickListener { presenter.plus() }
    }
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, onChanged: (T?) -> Unit) {
    observe(owner, Observer { onChanged(it) })
}
