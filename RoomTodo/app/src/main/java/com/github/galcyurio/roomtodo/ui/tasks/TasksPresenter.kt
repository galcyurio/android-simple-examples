package com.github.galcyurio.roomtodo.ui.tasks

import com.github.galcyurio.roomtodo.data.TaskDao
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TasksPresenter(
    private val taskDao: TaskDao
) : MvpBasePresenter<TasksContract.View>(), TasksContract.Presenter {
    private val disposable by lazy { CompositeDisposable() }
    private var offset = 0
    private val limit = 30

    override fun fetchTasks() {
        taskDao.findAll(limit, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { ifViewAttached { it.showLoading() } }
            .doFinally { ifViewAttached { it.hideLoading() } }
            .subscribeBy(
                onSuccess = { tasks -> ifViewAttached { it.showTasks(tasks) }; offset += limit },
                onError = { error -> ifViewAttached { it.showErrorMessage() }; error.printStackTrace() }
            )
            .addTo(disposable)

        // TODO: showTasks 호출 안되고 계속 로딩되는 현상 디버깅하기
    }

    override fun detachView() {
        super.detachView()
        disposable.clear()
    }
}