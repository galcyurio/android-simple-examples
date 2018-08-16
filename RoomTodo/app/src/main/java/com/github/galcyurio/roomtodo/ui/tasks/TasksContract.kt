package com.github.galcyurio.roomtodo.ui.tasks

import com.github.galcyurio.roomtodo.data.domain.Task
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface TasksContract {
    interface View: MvpView {
        fun showTasks(tasks: List<Task>)
        fun showErrorMessage()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter: MvpPresenter<View> {
        fun fetchTasks()
    }
}