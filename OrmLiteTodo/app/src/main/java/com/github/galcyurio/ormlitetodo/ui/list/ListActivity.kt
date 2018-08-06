package com.github.galcyurio.ormlitetodo.ui.list

import android.content.Intent
import android.os.Bundle
import com.github.galcyurio.ormlitetodo.R
import com.github.galcyurio.ormlitetodo.data.Task
import com.github.galcyurio.ormlitetodo.misc.DatabaseHelper
import com.github.galcyurio.ormlitetodo.misc.Events
import com.github.galcyurio.ormlitetodo.misc.RxBus
import com.github.galcyurio.ormlitetodo.misc.TaskAdapter
import com.github.galcyurio.ormlitetodo.ui.write.WriteActivity
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.j256.ormlite.android.apptools.OpenHelperManager
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class ListActivity : MvpActivity<ListContract.View, ListContract.Presenter>(), ListContract.View {
    private val adapter by lazy { TaskAdapter() }
    private val disposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recycler.adapter = adapter

        presenter.fetchTasks()

        fab.setOnClickListener {
            startActivity(Intent(this, WriteActivity::class.java))
        }

        RxBus.register(Events.TaskCreated::class.java)
            .subscribeBy {
                adapter.clear()
                presenter.fetchTasks()
            }
            .addTo(disposable)
    }

    override fun createPresenter(): ListContract.Presenter {
        /* TODO: kodein 을 통해 의존성 주입 */
        val taskDao = OpenHelperManager.getHelper<DatabaseHelper>(this, DatabaseHelper::class.java).taskDao
        return ListPresenter(taskDao)
    }

    override fun onDestroy() {
        super.onDestroy()
        OpenHelperManager.releaseHelper()
        disposable.clear()
    }

    override fun addTasks(tasks: List<Task>) {
        adapter.addTasks(tasks)
    }

    override fun showErrorMssage() {
        toast("에러 발생")
    }

    override fun showLoading() {
        loading.show()
    }

    override fun hideLoading() {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { loading.hide() }
            .addTo(disposable)
    }

    override fun showTaskDeleteAlert(): Maybe<Any> {
        return Maybe.create<Any> { emitter ->
            alert("삭제하시겠습니까?") {
                negativeButton("취소") { emitter.onComplete() }
                positiveButton("삭제한다") { emitter.onSuccess(true) }
            }.show()
        }
    }

}
