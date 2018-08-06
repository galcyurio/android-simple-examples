package com.github.galcyurio.ormlitetodo.ui.write

import android.util.Log
import com.github.galcyurio.ormlitetodo.data.Task
import com.github.galcyurio.ormlitetodo.misc.Events
import com.github.galcyurio.ormlitetodo.misc.RxBus
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.j256.ormlite.dao.Dao
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.sql.SQLException

class WritePresenter(
    private val taskDao: Dao<Task, Long>
) : MvpBasePresenter<WriteContract.View>(), WriteContract.Presenter {
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun write(title: String, description: String?) {
        if (title.isEmpty()) {
            ifViewAttached { it.showTitleEmptyMessage(); }
            return
        }

        Completable
            .create { emitter ->
                try {
                    val updatedRows = taskDao.create(Task(title, description))
                    when (updatedRows) {
                        0 -> emitter.onError(SQLException("Task를 테이블에 추가하지 못했습니다"))
                        else -> emitter.onComplete()
                    }
                } catch (e: SQLException) {
                    emitter.onError(e)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    ifViewAttached { it.close() }
                    RxBus.publish(Events.TaskCreated())
                },
                onError = { error ->
                    ifViewAttached { it.showErrorMessage() }
                    Log.e(this.javaClass.name, error.message, error)
                }
            )
            .addTo(disposable)
    }

    override fun detachView() {
        super.detachView()
        disposable.clear()
    }
}