package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.galcyurio.roomtodo.R
import com.github.galcyurio.roomtodo.misc.TaskItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_tasks.*
import org.jetbrains.anko.toast
import org.koin.android.architecture.ext.viewModel

class TasksActivity : AppCompatActivity() {
    private val viewModel: TasksViewModel by viewModel()
    private val disposable = CompositeDisposable()
    private val taskItemAdapter: TaskItemAdapter by lazy { TaskItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        recycler.adapter = TaskItemAdapter()

        viewModel.fetchTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading.show() }
            .doFinally { loading.hide() }
            .subscribeBy(
                onSuccess = taskItemAdapter::addTasks,
                onError = { error ->
                    toast("오류가 발생하였습니다")
                    error.printStackTrace()
                }
            )
            .addTo(disposable)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}
