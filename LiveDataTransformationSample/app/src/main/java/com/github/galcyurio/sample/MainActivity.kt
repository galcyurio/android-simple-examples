package com.github.galcyurio.sample

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.galcyurio.sample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = viewModel
        binding.setLifecycleOwner(this)

        btn.setOnClickListener {
            Log.i("Transformation", "name = ${viewModel.name.value}")
            Log.i("Transformation", "nickname = ${viewModel.nickname.value}")
        }
    }
}
