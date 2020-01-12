package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.galcyurio.roomtodo.R
import com.github.galcyurio.roomtodo.databinding.ActivityTasksBinding
import com.github.galcyurio.roomtodo.misc.TaskItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksActivity : AppCompatActivity() {
    private val viewModel: TasksViewModel by viewModel()
    private val disposable = CompositeDisposable()
    private lateinit var binding: ActivityTasksBinding

    private val taskItemAdapter: TaskItemAdapter by lazy { TaskItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)

        binding.recycler.adapter = taskItemAdapter

        viewModel.fetchTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { binding.loading.show() }
            .doFinally { binding.loading.hide() }
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
