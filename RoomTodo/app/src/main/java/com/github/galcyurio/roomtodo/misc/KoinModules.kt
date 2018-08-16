package com.github.galcyurio.roomtodo.misc

import android.arch.persistence.room.Room
import com.github.galcyurio.roomtodo.data.AppDatabase
import com.github.galcyurio.roomtodo.ui.tasks.TasksContract
import com.github.galcyurio.roomtodo.ui.tasks.TasksPresenter
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val module = applicationContext {
    bean {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "task-db").build()
    }

    bean { get<AppDatabase>().taskDao() }

    bean<TasksContract.Presenter> { TasksPresenter(get()) }
}