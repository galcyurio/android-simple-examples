package com.github.galcyurio.ormlitetodo.ui.list

import com.github.galcyurio.ormlitetodo.data.Task
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Maybe

interface ListContract {
    interface View : MvpView {
        fun addTasks(tasks: List<Task>)
        fun showErrorMssage()
        fun showLoading()
        fun hideLoading()
        fun showTaskDeleteAlert(): Maybe<Any>
    }

    interface Presenter : MvpPresenter<View> {
        fun fetchTasks()
        fun deleteTask(task: Task)
    }
}