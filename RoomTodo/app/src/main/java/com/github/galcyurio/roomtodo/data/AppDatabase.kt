package com.github.galcyurio.roomtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.galcyurio.roomtodo.data.domain.Task

@Database(
    entities = [Task::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}