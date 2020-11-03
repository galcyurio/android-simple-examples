package com.github.galcyurio.roomtodo.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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
        val context = InstrumentationRegistry.getInstrumentation().context
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

        val tasks = taskDao.findAll(0, 10)
        println("tasks = $tasks")
    }

    @Test
    fun dummyTest() {
        println("do nothing")
    }
}