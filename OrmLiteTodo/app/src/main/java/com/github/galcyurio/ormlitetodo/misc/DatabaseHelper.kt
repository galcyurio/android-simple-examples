package com.github.galcyurio.ormlitetodo.misc

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.github.galcyurio.ormlitetodo.R
import com.github.galcyurio.ormlitetodo.data.Task
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

class DatabaseHelper(
    context: Context
) : OrmLiteSqliteOpenHelper(
    context, "tasks.db", null, 2, R.raw.ormlite_config
) {
    val taskDao: Dao<Task, Long> = getDao(Task::class.java)

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(connectionSource, Task::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Task, Long>(connectionSource, Task::class.java, true)
        onCreate(database, connectionSource)
    }
}