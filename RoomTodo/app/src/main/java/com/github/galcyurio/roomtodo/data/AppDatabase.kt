package com.github.galcyurio.roomtodo.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.galcyurio.roomtodo.data.domain.Task

@Database(
    entities = [Task::class],
    version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}