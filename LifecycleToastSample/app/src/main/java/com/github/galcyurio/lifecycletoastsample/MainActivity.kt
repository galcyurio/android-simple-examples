package com.github.galcyurio.lifecycletoastsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        lifecycle.addObserver(presenter)
        btnLifecycle.setOnClickListener { showCurrentLifecycle() }
    }

    override fun showCurrentLifecycle() {
        // lifecycle.currentState -> 신뢰할 수 없음
        Log.i(MainActivity::class.java.name, lifecycle.currentState.name)
        Toast.makeText(this, lifecycle.currentState.name, Toast.LENGTH_SHORT).show()
    }
}
