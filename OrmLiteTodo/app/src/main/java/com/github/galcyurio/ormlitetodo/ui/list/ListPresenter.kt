package com.github.galcyurio.ormlitetodo.ui.list

import com.github.galcyurio.ormlitetodo.data.Task
import com.github.galcyurio.ormlitetodo.misc.Events
import com.github.galcyurio.ormlitetodo.misc.RxBus
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.j256.ormlite.dao.Dao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ListPresenter(
    private val taskDao: Dao<Task, Long>
) : MvpBasePresenter<ListContract.View>(), ListContract.Presenter {
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun attachView(view: ListContract.View) {
        super.attachView(view)

        RxBus.register(Events.TaskDoneChanged::class.java)
            .subscribe { event -> taskDao.update(event.task) }
            .addTo(disposable)



        RxBus.register(Events.TaskItemLongClicked::class.java)
            .subscribe { event -> ifViewAttached { it.showTaskDeleteAlert() } }


        /* TODO: alert 창 띄워서 확인 받고 삭제하기  */
        /* TODO: 2개 이상의 연속되는 observable 활용 */
    }

    override fun fetchTasks() {
        Single.fromCallable { taskDao.queryForAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { ifViewAttached { it.showLoading() } }
            .doFinally { ifViewAttached { it.hideLoading() } }
            .subscribeBy(
                onSuccess = { tasks -> ifViewAttached { it.addTasks(tasks) } },
                onError = { it.printStackTrace() }
            )
            .addTo(disposable)
    }

    override fun deleteTask(task: Task) {

    }

    override fun detachView() {
        super.detachView()
        disposable.clear()
    }
}