package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import com.github.galcyurio.roomtodo.R
import com.github.galcyurio.roomtodo.data.domain.Task
import com.github.galcyurio.roomtodo.misc.TaskItemAdapter
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_tasks.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class TasksActivity : MvpActivity<TasksContract.View, TasksContract.Presenter>(), TasksContract.View {
    private val taskItemAdapter: TaskItemAdapter by lazy { TaskItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        recycler.adapter = TaskItemAdapter()
    }

    override fun createPresenter(): TasksContract.Presenter {
        return inject<TasksContract.Presenter>().value
    }

    override fun showTasks(tasks: List<Task>) {
        taskItemAdapter.addTasks(tasks)
    }

    override fun showErrorMessage() {
        toast("오류가 발생하였습니다")
    }

    override fun showLoading() {
        loading.show()
    }

    override fun hideLoading() {
        loading.hide()
    }
}
