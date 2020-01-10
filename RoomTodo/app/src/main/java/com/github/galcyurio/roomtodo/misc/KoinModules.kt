package com.github.galcyurio.roomtodo.misc

import android.arch.persistence.room.Room
import com.github.galcyurio.roomtodo.data.AppDatabase
import com.github.galcyurio.roomtodo.ui.tasks.TasksViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val module = applicationContext {
    bean {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "task-db").build()
    }

    bean { get<AppDatabase>().taskDao() }

    viewModel { TasksViewModel(get()) }
}