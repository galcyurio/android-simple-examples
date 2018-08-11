package com.github.galcyurio.textholdermvvm.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.galcyurio.textholdermvvm.R
import com.github.galcyurio.textholdermvvm.misc.observe
import com.github.galcyurio.textholdermvvm.viewmodel.TextViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {
    private val textViewModel by viewModel<TextViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewModel.simpleText.observe(this) {
            tvTitle.text = it.title
            tvDetail.text = it.detail
        }

        fabAdd.setOnClickListener {
            startActivity(Intent(this, WriteActivity::class.java))
        }
    }
}
