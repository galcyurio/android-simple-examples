package com.github.galcyurio.lifecycletoastsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLifecycle.setOnClickListener { showCurrentLifecycle() }
    }

    override fun showCurrentLifecycle() {
        Toast.makeText(this, lifecycle.currentState.name, Toast.LENGTH_SHORT).show()
    }
}
