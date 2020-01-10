package com.github.galcyurio.roomtodo.ui.tasks

import androidx.lifecycle.ViewModel
import com.github.galcyurio.roomtodo.data.TaskDao
import com.github.galcyurio.roomtodo.data.domain.Task
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author galcyurio
 */
internal class TasksViewModel(
    private val taskDao: TaskDao
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val offset = AtomicInteger(0)
    private val limit = 30

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun fetchTasks(): Single<List<Task>> {
        return taskDao.findAll(limit, offset.get())
            .doOnSuccess { offset.addAndGet(limit) }
    }
}