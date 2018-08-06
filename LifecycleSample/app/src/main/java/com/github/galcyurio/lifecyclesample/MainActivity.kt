package com.github.galcyurio.lifecyclesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.galcyurio.lifecyclesample.misc.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val presenter = MainPresenter().addTo(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Toast.makeText(this, lifecycle.currentState.name, Toast.LENGTH_SHORT).show()
        }
    }
}
