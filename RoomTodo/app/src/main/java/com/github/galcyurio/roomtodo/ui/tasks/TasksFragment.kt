package com.github.galcyurio.roomtodo.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.galcyurio.roomtodo.databinding.FragmentTasksBinding
import com.github.galcyurio.roomtodo.misc.TaskItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author galcyurio
 */
internal class TasksFragment : Fragment() {
    private val viewModel: TasksViewModel by viewModel()
    private val disposable = CompositeDisposable()
    private lateinit var binding: FragmentTasksBinding

    private val taskItemAdapter: TaskItemAdapter by lazy { TaskItemAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        binding.recycler.adapter = taskItemAdapter

        viewModel.fetchTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { binding.loading.show() }
            .doFinally { binding.loading.hide() }
            .subscribeBy(
                onSuccess = taskItemAdapter::addTasks,
                onError = { error ->
                    Toast.makeText(activity, "오류가 발생하였습니다", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                }
            )
            .addTo(disposable)
        return binding.root
    }

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
    }
}