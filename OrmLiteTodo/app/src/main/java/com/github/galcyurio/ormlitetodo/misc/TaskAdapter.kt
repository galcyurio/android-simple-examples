package com.github.galcyurio.ormlitetodo.misc

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.galcyurio.ormlitetodo.R
import com.github.galcyurio.ormlitetodo.data.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val tasks: MutableList<Task> by lazy { mutableListOf<Task>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]
        with(holder.itemView) {
            check.isChecked = task.isDone
            title.text = task.title

            check.setOnCheckedChangeListener { _, isChecked ->
                task.isDone = isChecked
                RxBus.publish(Events.TaskDoneChanged(task))
            }

            this.setOnLongClickListener {
                RxBus.publish(Events.TaskItemLongClicked(task))
                true
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTasks(tasks: List<Task>) {
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun clear() {
        this.tasks.clear()
    }
}
