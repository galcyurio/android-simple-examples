package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.galcyurio.roomtodo.R
import com.github.galcyurio.roomtodo.misc.TaskItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_tasks.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksActivity : AppCompatActivity() {
    private val viewModel: TasksViewModel by viewModel()
    private val disposable = CompositeDisposable()
    private val taskItemAdapter: TaskItemAdapter by lazy { TaskItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        recycler.adapter = taskItemAdapter

        viewModel.fetchTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading.show() }
            .doFinally { loading.hide() }
            .subscribeBy(
                onSuccess = taskItemAdapter::addTasks,
                onError = { error ->
                    Toast.makeText(this, "오류가 발생하였습니다", Toast.LENGTH_SHORT).show()
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
