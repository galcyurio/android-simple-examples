package com.github.galcyurio.roomtodo.misc

import androidx.room.Room
import com.github.galcyurio.roomtodo.data.AppDatabase
import com.github.galcyurio.roomtodo.ui.tasks.TasksViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "task-db").build()
    }

    single { get<AppDatabase>().taskDao() }

    viewModel { TasksViewModel(get()) }
}