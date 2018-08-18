package com.github.galcyurio.livedataupdownsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = NumberViewModel()
        btnUp.setOnClickListener { viewModel.plus() }
        btnDown.setOnClickListener { viewModel.minus() }
    }
}
