package com.github.galcyurio.roomtodo.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.github.galcyurio.roomtodo.data.domain.Task
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var taskDao: TaskDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        taskDao = appDatabase.taskDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun insert() {
        val task = Task(task = "dummy task", detail = "dummy detail")
        taskDao.insert(task)

        val tasks = taskDao.findAll()
        println("tasks = $tasks")
    }

    @Test
    fun dummyTest() {
        println("do nothing")
    }
}