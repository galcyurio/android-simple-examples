package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.galcyurio.roomtodo.R
import com.github.galcyurio.roomtodo.databinding.ActivityTasksBinding

class TasksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)
    }
}
