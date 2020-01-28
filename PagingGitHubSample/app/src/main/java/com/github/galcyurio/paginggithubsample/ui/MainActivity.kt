package com.github.galcyurio.paginggithubsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.github.galcyurio.paginggithubsample.R
import com.github.galcyurio.paginggithubsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val userListAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainViewModel()

        binding.recyclerView.adapter = userListAdapter

        viewModel.users.observe(this, Observer {
            userListAdapter.submitList(it)
        })
    }
}
