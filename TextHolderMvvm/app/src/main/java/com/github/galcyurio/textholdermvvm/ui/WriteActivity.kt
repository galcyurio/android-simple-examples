package com.github.galcyurio.textholdermvvm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.galcyurio.textholdermvvm.R
import com.github.galcyurio.textholdermvvm.misc.string
import com.github.galcyurio.textholdermvvm.viewmodel.TextViewModel
import kotlinx.android.synthetic.main.activity_write.*
import org.koin.android.architecture.ext.viewModel

class WriteActivity : AppCompatActivity() {
    private val textViewModel by viewModel<TextViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        fabComplete.setOnClickListener {
            textViewModel.save(etTitle.string(), etDetail.string())
            finish()
        }
    }
}
