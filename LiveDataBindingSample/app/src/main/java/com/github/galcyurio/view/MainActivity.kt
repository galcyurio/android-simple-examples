package com.github.galcyurio.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.galcyurio.R
import com.github.galcyurio.databinding.ActivityMainBinding
import com.github.galcyurio.misc.Injector
import com.github.galcyurio.misc.KittyAdapter
import com.github.galcyurio.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var kittyAdapter: KittyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        kittyAdapter = KittyAdapter()
        kitties.adapter = kittyAdapter

        val viewModel = ViewModelProviders
            .of(this, Injector.provideMainViewModelFactory())
            .get(MainViewModel::class.java)

        viewModel.kitties.observe(this, Observer { newKitties ->
            newKitties?.let { kittyAdapter.submitList(it) }
        })
    }
}
